package ru.gorbach.hw22.order.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.KeyGenerationError;
import ru.gorbach.hw22.common.business.exception.jdbc.SqlError;
import ru.gorbach.hw22.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.gorbach.hw22.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.gorbach.hw22.common.solutions.repo.jdbc.QueryWrapper;
import ru.gorbach.hw22.common.solutions.repo.jdbc.ResultSetExtractor;
import ru.gorbach.hw22.order.domain.Order;
import ru.gorbach.hw22.order.repo.OrderRepo;
import ru.gorbach.hw22.order.search.OrderSearchCondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDBRepo implements OrderRepo {
    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder selectDataHolder = getSearchSqlAndPrStmtHolder(searchCondition);
            return QueryWrapper.select(selectDataHolder.getSql(), OrderMapper::mapOrder,
                    ps -> {
                        for (PreparedStatementConsumer statementConsumer : selectDataHolder.getPreparedStatementConsumers()) {
                            statementConsumer.consume(ps);
                        }
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(OrderSearchCondition searchCondition) {
        String sql = "SELECT * FROM ORDER";

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

            String whereStr = String.join("AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr);

            if (searchCondition.needPagination()){
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(OrderSearchCondition orderSearchCondition){
        return " LIMIT " + orderSearchCondition.getPaginator().getLimit() + " OFFSET " + orderSearchCondition.getPaginator().getOffset();
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

//    @Override
//    public void deleteByUserId(long userId) {
//        try {
//            QueryWrapper.executeUpdate("DELETE FROM ORDER_TAB WHERE USER_ID = ?",
//                    ps -> {
//                        ps.setLong(1, userId);
//                    });
//        } catch (Exception e) {
//            throw new SqlError(e);
//        }
//    }

//    @Override
//    public List<Order> findByUserId(long userId) {
//        try {
//            String sql = "SELECT * FROM ORDER_TAB WHERE USER_ID = ?";
//            return QueryWrapper.select(sql, OrderMapper::mapOrder, ps -> {
//                ps.setLong(1, userId);
//            });
//        } catch (Exception e) {
//            throw new SqlError(e);
//        }
//    }

    @Override
    public Order add(Order order) {
        return new Order();
//        return insert(order, null);
    }

//    @Override
//    public Order insertTx(Order order, Connection connection) {
//        return add(order, connection);
//    }

    private Order add(Order order, Connection connection) {
        try {

            Optional<Long> optionalId;
            PreparedStatementConsumer psConsumer = ps -> appendPsValuesForInsertOrder(ps, order);
            ResultSetExtractor<Long> rsExtractor = rs -> rs.getLong("ID");

            if (connection == null) {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), psConsumer, rsExtractor);
            } else {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), connection, psConsumer, rsExtractor);
            }

            if (optionalId.isPresent()) {
                order.setId(optionalId.get());
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

    private void appendPsValuesForInsertOrder(PreparedStatement ps, Order order) throws SQLException {
//        int index = 0;
//        ps.setLong(++index, order.getUser().getId());
//        ps.setLong(++index, order.getMark().getId());
//        ps.setLong(++index, order.getModel().getId());
//        ps.setString(++index, order.getDescription());
//        ps.setInt(++index, order.getPrice());
    }

    private String getInsertOrderSql() {
        return "INSERT INTO ORDER_TAB (USER_ID, MARK_ID, MODEL_ID, DESCRIPTION, PRICE) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void add(Collection<Order> orders) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertOrderSql(), orders, this::appendPsValuesForInsertOrder);
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
        deleteById(id, null);
    }

//    @Override
//    public void deleteByIdTx(long id, Connection connection) {
//        deleteById(id, connection);
//    }

    private void deleteById(Long id, Connection connection) {
        try {
            String sql = "DELETE FROM USER WHERE ID = ?";
            PreparedStatementConsumer psConsumer = ps -> ps.setLong(1, id);
            if (connection == null) {
                QueryWrapper.executeUpdate(sql, psConsumer);
            } else {
                QueryWrapper.executeUpdate(sql, connection, psConsumer);
            }
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
}
