package ru.gorbach.hw22.country.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.KeyGenerationError;
import ru.gorbach.hw22.common.business.exception.jdbc.SqlError;
import ru.gorbach.hw22.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import ru.gorbach.hw22.common.business.search.OrderDirection;
import ru.gorbach.hw22.common.business.search.OrderType;
import ru.gorbach.hw22.common.solutions.repo.jdbc.PreparedStatementConsumer;
import ru.gorbach.hw22.common.solutions.repo.jdbc.QueryWrapper;
import ru.gorbach.hw22.country.domain.Country;
import ru.gorbach.hw22.country.domain.CountryDiscriminator;
import ru.gorbach.hw22.country.domain.CountryWithColdClimate;
import ru.gorbach.hw22.country.domain.CountryWithHotClimate;
import ru.gorbach.hw22.country.exception.unchecked.UnknownCountryDisriminatorException;
import ru.gorbach.hw22.country.repo.CountryRepo;
import ru.gorbach.hw22.country.search.CountrySearchCondition;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.sql.Types.*;
import static ru.gorbach.hw22.country.domain.CountryDiscriminator.HOT;
import static ru.gorbach.hw22.country.repo.impl.jdbc.CountryMapper.mapColdCountry;
import static ru.gorbach.hw22.country.repo.impl.jdbc.CountryMapper.mapHotCountry;

public class CountryDBRepo implements CountryRepo {
    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("NAME", "LANGUAGE");

    @Override
    public Country add(Country country) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertCountrySql(),
                    ps -> {
                        appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                country.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return country;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void add(Collection<Country> countries) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertCountrySql(), countries,
                    (ps, country) -> appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Country country) {
        String sql =
                "UPDATE COUNTRY " +
                        "SET " +
                        "NAME = ? ," +
                        "LANGUAGE = ?," +
                        "DISCRIMINATOR = ? ," +
                        "PHONE_CODE = ? ," +
                        "POLAR_NIGHT = ? ," +
                        "HOTTEST_MONTH = ?," +
                        "AVERAGE_TEMP = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger(0);
                        appendPreparedStatementParamsForCountry(index, ps, country);

                        switch (country.getDiscriminator()) {

                            case HOT: {
                                appendPreparedStatementParamsForHotCountry(index, ps, (CountryWithHotClimate) country);
                                break;
                            }
                            case COLD: {
                                appendPreparedStatementParamsForColdCountry(index, ps, (CountryWithColdClimate) country);
                                break;
                            }
                        }
                        ps.setLong(index.incrementAndGet(), country.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Country> findById(Long id) {
        try {
            return QueryWrapper.selectOne("SELECT * FROM COUNTRY",
                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CountryDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> HOT.equals(discriminator) ? mapHotCountry(rs) : mapColdCountry(rs))
                                .orElseThrow(UnknownCountryDisriminatorException::new);
                    },
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
            QueryWrapper.executeUpdate("DELETE FROM COUNTRY WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
//        findAllCountrysFetchingModels().forEach(System.out::println);
    }

    @Override
    public List<Country> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM COUNTRY",
                    rs -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CountryDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> HOT.equals(discriminator) ? mapHotCountry(rs) : mapColdCountry(rs))
                                .orElseThrow(UnknownCountryDisriminatorException::new);
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM COUNTRY",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<? extends Country> search(CountrySearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    rs -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CountryDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> HOT.equals(discriminator) ? mapHotCountry(rs) : mapColdCountry(rs))
                                .orElseThrow(UnknownCountryDisriminatorException::new);
                    },
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


    private void appendPreparedStatementParamsForCountry(AtomicInteger index, PreparedStatement ps, Country country) throws SQLException {
        ps.setString(index.incrementAndGet(), country.getName());
        ps.setString(index.incrementAndGet(), country.getLanguage());
        CountryDiscriminator discriminator = country.getDiscriminator();
        ps.setString(index.incrementAndGet(), discriminator.toString());
    }

    private void appendPreparedStatementParamsForHotCountry(AtomicInteger index, PreparedStatement ps, CountryWithHotClimate hotCountry) throws SQLException {
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), BOOLEAN);
        ps.setString(index.incrementAndGet(), hotCountry.getHottestMonth().toString());
        ps.setDouble(index.incrementAndGet(), hotCountry.getAverageTemp());
    }

    private void appendPreparedStatementParamsForColdCountry(AtomicInteger index, PreparedStatement ps, CountryWithColdClimate coldCountry) throws SQLException {
        ps.setInt(index.incrementAndGet(), coldCountry.getTelephoneCode());
        ps.setBoolean(index.incrementAndGet(), coldCountry.getPolarNight());
        ps.setNull(index.incrementAndGet(), VARCHAR);
        ps.setNull(index.incrementAndGet(), DOUBLE);
    }


    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CountrySearchCondition searchCondition) {
        String sql = "SELECT * FROM COUNTRY";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByName()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getName()));
            }

            if (searchCondition.searchByLanguage()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getLanguage()));
            }

            String whereStr = String.join(" AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);

            if (searchCondition.needPagination()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(CountrySearchCondition countrySearchCondition) {
        return " LIMIT " + countrySearchCondition.getPaginator().getLimit() + " OFFSET " + countrySearchCondition.getPaginator().getOffset();
    }

    private String getOrdering(CountrySearchCondition searchCondition) {
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

    private String getInsertCountrySql() {
        return "INSERT INTO MODEL (" +
                "NAME," +
                "LANGUAGE," +
                "DISCRIMINATOR," +
                "PHONE_CODE," +
                "POLAR_NIGHT," +
                "HOTTEST_MONTH," +
                "AVERAGE_TEMP)" +
                "VALUES(?,?,?,?,?,?,?)";
    }
}
