package ru.gorbach.hw22.city.repo.impl.jdbc;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.common.business.exception.jdbc.ResultSetMappingException;

import java.sql.ResultSet;

public final class CityMapper {
    private static final String CITY_CLASS_NAME = City.class.getSimpleName();

    private CityMapper() {
    }

    public static City mapCity(ResultSet rs) throws ResultSetMappingException {
        try {
            City city = new City();
            city.setId(rs.getLong("ID"));
            city.setCountryId(rs.getLong("COUNTRY_ID"));
            city.setName(rs.getString("NAME"));
            city.setPopulation(rs.getInt("POPULATION"));
            city.setCapital(rs.getBoolean("CAPITAL"));

            return city;
        } catch (Exception e) {
            throw new ResultSetMappingException(CITY_CLASS_NAME, e);
        }
    }
}
