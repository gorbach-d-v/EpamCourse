package ru.gorbach.hw22.city.repo.impl.jdbc;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.city.repo.CityRepo;
import ru.gorbach.hw22.city.search.CitySearchCondition;
import ru.gorbach.hw22.common.business.exception.jdbc.KeyGenerationError;
import ru.gorbach.hw22.common.business.exception.jdbc.SqlError;
import ru.gorbach.hw22.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.gorbach.hw22.common.business.search.OrderDirection;
import ru.gorbach.hw22.common.business.search.OrderType;
import ru.gorbach.hw22.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.gorbach.hw22.common.solutions.repo.jdbc.QueryWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CityDBRepo implements CityRepo {
    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("COUNTRY_ID", "CAPITAL", "NAME", "POPULATION");

    @Override
    public City add(City city) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getAddCitySql(),
                    ps -> {
                        appendPreparedStatementParamsForCity(new AtomicInteger(0), ps, city);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                city.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return city;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void add(Collection<City> cities) {
        try {
            QueryWrapper.executeUpdateAsBatch(getAddCitySql(), cities,
                    (ps, city) -> appendPreparedStatementParamsForCity(new AtomicInteger(0), ps, city));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(City city) {
        String sql = "UPDATE CITY SET COUNTRY_ID = ?, NAME = ?, POPULATION = ?, CAPITAL = ?, WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForCity(index, ps, city);
                        ps.setLong(index.incrementAndGet(), city.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        try {
            String sql = "SELECT * FROM CITY WHERE ID = ?";
            return QueryWrapper.selectOne(sql,
                    CityMapper::mapCity,
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
            QueryWrapper.executeUpdate("DELETE FROM CITY WHERE ID = ?", ps -> {
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
    public List<City> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM CITY", CityMapper::mapCity);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM CITY",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    CityMapper::mapCity,
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

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CitySearchCondition searchCondition) {
        String sql = "SELECT * FROM CITY";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByCountryId()) {
                where.add("(COUNTRY_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCountryId()));
            }

            if (searchCondition.searchByName()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getName()));
            }

            if (searchCondition.searchByPopulation()) {
                where.add("(POPULATION = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getPopulation()));
            }

            if (searchCondition.searchByCapital()) {
                where.add("(CAPITAL = ?)");
                psConsumers.add(ps -> ps.setBoolean(index.incrementAndGet(), searchCondition.isCapital()));
            }

            String whereStr = String.join(" AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);

            if (searchCondition.needPagination()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(CitySearchCondition citySearchCondition) {
        return " LIMIT " + citySearchCondition.getPaginator().getLimit() + " OFFSET " + citySearchCondition.getPaginator().getOffset();
    }

    private String getOrdering(CitySearchCondition searchCondition) {

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

    private String getAddCitySql() {
        return "INSERT INTO CITY ( COUNTRY_ID, NAME, POPULATION, CAPITAL ) VALUES (?, ?, ?, ?)";
    }

    private void appendPreparedStatementParamsForCity(AtomicInteger index, PreparedStatement ps, City city) throws SQLException {
        ps.setLong(index.incrementAndGet(), city.getCountryId());
        ps.setString(index.incrementAndGet(), city.getName());
        ps.setInt(index.incrementAndGet(), city.getPopulation());
        ps.setBoolean(index.incrementAndGet(), city.isCapital());
    }
}