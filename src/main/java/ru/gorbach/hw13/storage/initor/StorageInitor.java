package ru.gorbach.hw13.storage.initor;

import ru.gorbach.hw13.country.domain.Country;
import ru.gorbach.hw13.country.service.CountryService;
import ru.gorbach.hw13.storage.initor.datareader.*;
import ru.gorbach.hw13.storage.initor.datareader.saxreader.SaxXmlDataReader;
import ru.gorbach.hw13.storage.initor.datareader.staxreader.StaxXmlDataReader;

import java.util.ArrayList;
import java.util.List;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, DOM_XML_FILE, SAX_XML_FILE, STAX_XML_FILE
    }

    public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {
        List<Country> countryList = getMarksFromStorage(filePath, dataSourceType);

        if (!countryList.isEmpty()) {
            for (Country country : countryList) {
                countryService.add(country);
            }
        }
    }

    private List<Country> getMarksFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

        List<Country> countries = new ArrayList<>();
        DataReader dataReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataReader = new TxtDataReader();
                break;
            }
            case DOM_XML_FILE: {
                dataReader = new DomXmlDataReader();
                break;
            }
            case SAX_XML_FILE: {
                dataReader = new SaxXmlDataReader();
                break;
            }
            case STAX_XML_FILE: {
                dataReader = new StaxXmlDataReader();
                break;
            }
        }

        if (dataReader != null) {
            countries = dataReader.getDataFromFile(filePath);
        }
        return countries;
    }
}
