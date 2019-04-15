package ru.gorbach.hw22.order.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.KeyGenerationError;
import ru.gorbach.hw22.common.business.exception.jdbc.SqlError;
import ru.gorbach.hw22.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.gorbach.hw22.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.gorbach.hw22.common.solutions.repo.jdbc.QueryWrapper;
import ru.gorbach.hw22.order.domain.Order;
import ru.gorbach.hw22.order.repo.OrderRepo;
import ru.gorbach.hw22.order.search.OrderSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDBRepo implements OrderRepo {

    @Override
    public Order add(Order order) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getAddOrderSql(),
                    ps -> {
                        appendPreparedStatementParamsForOrder(new AtomicInteger(0), ps, order);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                order.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return order;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void add(Collection<Order> orders) {
        try {
            QueryWrapper.executeUpdateAsBatch(getAddOrderSql(), orders,
                    (ps, customer) -> appendPreparedStatementParamsForOrder(new AtomicInteger(0), ps, customer));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("No update operation for order");
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            String sql = "SELECT * FROM ORDER_TAB WHERE ID = ?";
            return QueryWrapper.selectOne(sql, OrderMapper::mapOrder, ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM ORDER_TAB WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<Order> findAll() {
        try {
            String sql = "SELECT * FROM ORDER_TAB ";
            return QueryWrapper.select(sql, OrderMapper::mapOrder);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(), OrderMapper::mapOrder,
                    ps -> {
                        List<PreparedStatementConsumer> psConsumers = sqlParamsHolder.getPreparedStatementConsumers();
                        for (PreparedStatementConsumer consumer : psConsumers) {
                            consumer.consume(ps);
                        }
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countByCity(Long cityIdId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE CITY_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, cityIdId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countByCountry(Long countryId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE COUNTRY_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, countryId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(OrderSearchCondition searchCondition) {
        String sql = "SELECT * FROM ORDER_TAB";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByCustomerId()) {
                where.add("(USER_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCustomerId()));
            }

            if (searchCondition.searchByCountryId()) {
                where.add("(COUNTRY_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCountryId()));
            }

            if (searchCondition.searchByCityId()) {
                where.add("(CITY_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCityId()));
            }

            if (searchCondition.searchByPrice()) {
                where.add("(PRICE = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getPrice()));
            }

            String whereStr = String.join("AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr);

            if (searchCondition.needPagination()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(OrderSearchCondition orderSearchCondition) {
        return " LIMIT " + orderSearchCondition.getPaginator().getLimit() + " OFFSET " + orderSearchCondition.getPaginator().getOffset();
    }

    private String getAddOrderSql() {
        return "INSERT INTO ORDER_TAB (USER_ID, COUNTRY_ID, CITY_ID, PRICE) VALUES (?, ?, ?, ?)";
    }

    private void appendPreparedStatementParamsForOrder(AtomicInteger index, PreparedStatement ps, Order order) throws SQLException {
        ps.setLong(index.incrementAndGet(), order.getCustomer().getId());
        ps.setLong(index.incrementAndGet(), order.getCountry().getId());
        ps.setLong(index.incrementAndGet(), order.getCity().getId());
        ps.setInt(index.incrementAndGet(), order.getPrice());
    }
}
