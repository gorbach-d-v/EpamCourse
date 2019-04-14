package ru.gorbach.hw19.storage.initor.datareader;

import ru.gorbach.hw19.city.domain.City;
import ru.gorbach.hw19.country.domain.*;
import ru.gorbach.hw19.storage.initor.exception.checked.InitDataCheckedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.gorbach.hw19.storage.initor.exception.InitDataExceptionMeta.PARSE_COUNTRY_DISCRIMINATOR_ERROR;

public class TxtDataReader implements DataReader {
    private static final String COUNTRY_PLACEHOLDER = "Country:";

    public List<Country> getDataFromFile(String file) throws Exception {
        List<String> fileAsList = readFileToList(file);

        List<Country> result = new ArrayList<>();
        if (!fileAsList.isEmpty()) {
            List<List<String>> countriesWithCities = splitFileToSeparateCountryWithSities(fileAsList);

            for (List<String> countryWithCities : countriesWithCities) {
                result.add(parseCountry(countryWithCities));
            }
        }

        return result;
    }

    private List<String> readFileToList(String path) throws IOException {
        List<String> resultList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultList.add(line);
            }
        }
        return resultList;
    }

    private List<List<String>> splitFileToSeparateCountryWithSities(List<String> fileAsList) {
        List<List<String>> countryWithCities = new ArrayList<>();

        List<String> singleCountryWithCities = null;
        for (String dataFromFile : fileAsList) {
            if (!dataFromFile.isEmpty()) {

                if (dataFromFile.contains(COUNTRY_PLACEHOLDER)) {
                    if (singleCountryWithCities != null && !singleCountryWithCities.isEmpty()) {
                        countryWithCities.add(singleCountryWithCities);
                    }
                    singleCountryWithCities = new ArrayList<>();
                    singleCountryWithCities.add(dataFromFile);
                } else if (singleCountryWithCities != null) {
                    singleCountryWithCities.add(dataFromFile);
                }

            }
        }

        return countryWithCities;
    }

    private Country parseCountry(List<String> countryWithCities) throws InitDataCheckedException {
        String countryAsStr = countryWithCities.get(0).replaceAll(COUNTRY_PLACEHOLDER, "");
        countryWithCities.remove(0);

        return getCountry(countryAsStr, countryWithCities);
    }

    private Country getCountry(String countryAsStr, List<String> citiesAsStr) throws InitDataCheckedException {
        String[] countryAttrs = countryAsStr.split("\\|");

        int attrIndex = -1;
        String discriminatorAsStr = countryAttrs[++attrIndex].trim();
        Country country = createCountryByDiscriminator(discriminatorAsStr,
                countryAttrs[++attrIndex].trim(),
                countryAttrs[++attrIndex].trim()
        );
        country.setCities(getCityList(citiesAsStr));
        if (CountryWithHotClimate.class.equals(country.getClass())) {
            appendHotCountryAttributes((CountryWithHotClimate) country, countryAttrs, attrIndex);
        } else if (CountryWithColdClimate.class.equals(country.getClass())) {
            appendColdCountryAttributes((CountryWithColdClimate) country, countryAttrs, attrIndex);
        }

        return country;
    }

    private List<City> getCityList(List<String> citiesListAsStr) {
        List<City> result = new ArrayList<>();
        for (String cityStr : citiesListAsStr) {
            if (cityStr != null) {
                String[] cityAttrs = cityStr.split("\\|");

                int attrIndex = -1;

                City city = new City(cityAttrs[++attrIndex].trim(),
                        Integer.parseInt(cityAttrs[++attrIndex].trim()),
                        Boolean.parseBoolean(cityAttrs[++attrIndex].trim()));
                result.add(city);
            }
        }
        return result;
    }

    private Country createCountryByDiscriminator(String discriminatorAsStr, String name, String language) throws InitDataCheckedException {
        if (CountryDiscriminator.isDiscriminatorNotExists(discriminatorAsStr)) {
            throw new InitDataCheckedException(
                    PARSE_COUNTRY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(discriminatorAsStr),
                    PARSE_COUNTRY_DISCRIMINATOR_ERROR.getCode()
            );
        } else {
            CountryDiscriminator discriminator = CountryDiscriminator.getDiscriminatorByName(discriminatorAsStr);
            if (CountryDiscriminator.HOT.equals(discriminator)) {
                return new CountryWithHotClimate(name, language);
            }
            return new CountryWithColdClimate(name, language);
        }
    }

    private void appendHotCountryAttributes(CountryWithHotClimate country, String[] attrs, int attrIndex) {
        String a = attrs[++attrIndex].trim();
        if (Month.isStrBelongsToEnumValues(a)) {
            country.setHottestMonth(Month.valueOf(a));
        }
        country.setAverageTemp(Double.parseDouble(attrs[++attrIndex].trim()));
    }

    private void appendColdCountryAttributes(CountryWithColdClimate country, String[] attrs, int attrIndex) {
        country.setTelephoneCode(Integer.parseInt(attrs[++attrIndex].trim()));
        country.setPolarNight(Boolean.parseBoolean(attrs[++attrIndex].trim()));
    }

}
