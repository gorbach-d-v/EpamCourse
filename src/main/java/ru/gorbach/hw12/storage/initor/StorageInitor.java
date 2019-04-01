package ru.gorbach.hw12.storage.initor;

import ru.gorbach.hw12.country.domain.Country;
import ru.gorbach.hw12.country.service.CountryService;
import ru.gorbach.hw12.storage.initor.datareader.DataReader;
import ru.gorbach.hw12.storage.initor.datareader.TxtDataReader;
import ru.gorbach.hw12.storage.initor.datareader.XmlDataReader;

import java.util.ArrayList;
import java.util.List;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {
        List<Country> countryList = getCountriesFromStorage(filePath, dataSourceType);

        if (!countryList.isEmpty()) {
            for (Country country : countryList) {
                countryService.add(country);
            }
        }
    }

    private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

        List<Country> countries = new ArrayList<>();
        DataReader dataReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataReader = new TxtDataReader();
                break;
            }
            case XML_FILE: {
                dataReader = new XmlDataReader();
                break;
            }
        }

        if (dataReader != null) {
            countries = dataReader.getDataFromFile(filePath);
        }
        return countries;
    }
}
