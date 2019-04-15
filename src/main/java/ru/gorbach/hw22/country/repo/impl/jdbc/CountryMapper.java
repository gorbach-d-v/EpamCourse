package ru.gorbach.hw22.country.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.ResultSetMappingException;
import ru.gorbach.hw22.country.domain.Country;
import ru.gorbach.hw22.country.domain.CountryWithColdClimate;
import ru.gorbach.hw22.country.domain.CountryWithHotClimate;
import ru.gorbach.hw22.country.domain.Month;
import ru.gorbach.hw22.country.exception.unchecked.UnknownMonthException;

import java.sql.ResultSet;
import java.util.Optional;

public class CountryMapper {

    private static final String COUNTRY_CLASS_NAME = Country.class.getSimpleName();

    private CountryMapper() {
    }

    public static CountryWithHotClimate mapHotCountry(ResultSet rs) throws ResultSetMappingException {
        try {
            String hottestMonth = rs.getString("HOTTEST_MONTH");
            Optional<Month> monthByStrValue = Month.getMonthByStrValue(hottestMonth);

            if (!monthByStrValue.isPresent()) {
                throw new UnknownMonthException(hottestMonth);
            }

            CountryWithHotClimate hotCountry = new CountryWithHotClimate();
            hotCountry.setHottestMonth(monthByStrValue.get());
            hotCountry.setAverageTemp(rs.getDouble("AVERAGE_TEMPERATURE"));

            mapCommonCountryData(hotCountry, rs);
            return hotCountry;
        } catch (UnknownMonthException e) {
            throw e;
        } catch (Exception e) {
            throw new ResultSetMappingException(COUNTRY_CLASS_NAME, e);
        }
    }

    public static CountryWithColdClimate mapColdCountry(ResultSet rs) throws ResultSetMappingException {
        try {
            CountryWithColdClimate coldCountry = new CountryWithColdClimate();
            coldCountry.setTelephoneCode(rs.getInt("PHONE_CODE"));
            coldCountry.setPolarNight(rs.getBoolean("POLAR_NIGHT"));

            mapCommonCountryData(coldCountry, rs);
            return coldCountry;
        } catch (Exception e) {
            throw new ResultSetMappingException(COUNTRY_CLASS_NAME, e);
        }
    }

    public static void mapCommonCountryData(Country country, ResultSet rs) throws ResultSetMappingException {
        try {
            country.setId(rs.getLong("ID"));
            country.setName(rs.getString("NAME"));
            country.setLanguage(rs.getString("LANGUAGE"));
        } catch (Exception e) {
            throw new ResultSetMappingException(COUNTRY_CLASS_NAME, e);
        }
    }
}
