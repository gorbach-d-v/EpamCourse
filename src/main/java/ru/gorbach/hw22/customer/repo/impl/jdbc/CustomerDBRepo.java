package ru.gorbach.hw22.customer.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.KeyGenerationError;
import ru.gorbach.hw22.common.business.exception.jdbc.SqlError;
import ru.gorbach.hw22.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.gorbach.hw22.common.business.search.OrderDirection;
import ru.gorbach.hw22.common.business.search.OrderType;
import ru.gorbach.hw22.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.gorbach.hw22.common.solutions.repo.jdbc.QueryWrapper;
import ru.gorbach.hw22.customer.domain.Customer;
import ru.gorbach.hw22.customer.repo.CustomerRepo;
import ru.gorbach.hw22.customer.search.CustomerSearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerDBRepo implements CustomerRepo {
    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("FIRST_NAME", "LAST_NAME");

    @Override
    public Customer add(Customer customer) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertCustomerSql(),
                    ps -> {
                        appendPreparedStatementParamsForCustomer(new AtomicInteger(0), ps, customer);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                customer.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return customer;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void add(Collection<Customer> customers) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertCustomerSql(), customers,
                    (ps, customer) -> appendPreparedStatementParamsForCustomer(new AtomicInteger(0), ps, customer));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE USER SET FIRST_NAME = ?, LAST_NAME = ?, WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForCustomer(index, ps, customer);
                        ps.setLong(index.incrementAndGet(), customer.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try {
            String sql = "SELECT * FROM USER WHERE ID = ?";
            return QueryWrapper.selectOne(sql,
                    CustomerMapper::mapCustomer,
                    ps -> {
                        ps.setLong(1, id);
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM USER WHERE ID = ?", ps -> {
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
    public List<Customer> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM USER", CustomerMapper::mapCustomer);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM USER",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Customer> search(CustomerSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    CustomerMapper::mapCustomer,
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

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CustomerSearchCondition searchCondition) {
        String sql = "SELECT * FROM USER";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByFirstName()) {
                where.add("(FIRST_NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getFirstName()));
            }

            if (searchCondition.searchByLastName()) {
                where.add("(LAST_NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getLastName()));
            }
            String whereStr = String.join(" AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);

            if (searchCondition.needPagination()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(CustomerSearchCondition customerSearchCondition) {
        return " LIMIT " + customerSearchCondition.getPaginator().getLimit() + " OFFSET " + customerSearchCondition.getPaginator().getOffset();
    }

    private String getOrdering(CustomerSearchCondition searchCondition) {

        if (searchCondition.needOrdering()) {
            OrderType orderType = searchCondition.getOrderType();
            OrderDirection orderDirection = searchCondition.getOrderDirection();

            switch (orderType) {

                case SIMPLE: {
                    return " ORDER BY " + searchCondition.getOrderByField().name() + " " + orderDirection;
                }
                case COMPLEX: {
                    return " ORDER BY " + String.join(", ", COMPLEX_ORDER_FIELDS) + " " + orderDirection;
                }
            }
        }
        return "";
    }

    private String getInsertCustomerSql() {
        return "INSERT INTO USER ( FIRST_NAME, LAST_NAME ) VALUES (?, ?)";
    }

    private void appendPreparedStatementParamsForCustomer(AtomicInteger index, PreparedStatement ps, Customer customer) throws SQLException {
        ps.setString(index.incrementAndGet(), customer.getFirstName());
        ps.setString(index.incrementAndGet(), customer.getLastName());
    }






}


