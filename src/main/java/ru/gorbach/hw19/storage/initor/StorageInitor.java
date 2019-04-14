package ru.gorbach.hw19.storage.initor;

import ru.gorbach.hw19.country.domain.Country;
import ru.gorbach.hw19.country.service.CountryService;
import ru.gorbach.hw19.storage.initor.exception.checked.ParseXmlException;

import java.util.ArrayList;
import java.util.List;

import static ru.gorbach.hw19.storage.initor.exception.InitDataExceptionMeta.PARSE_XML_EXCEPTION;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, DOM_XML_FILE, SAX_XML_FILE, STAX_XML_FILE
    }

    public void initStorageWithCountriesAndCities(List<String> filePaths, DataSourceType dataSourceType) throws Exception {
        List<AsyncCountryParser> asyncCountryParsers = prepareParsers(filePaths, dataSourceType);
        List<Country> countriesToPersist = asyncParseFilesAndWaitForResult(asyncCountryParsers);

        countryService.add(countriesToPersist);
    }

    private List<AsyncCountryParser> prepareParsers(List<String> filePaths, DataSourceType dataSourceType) {
        List<AsyncCountryParser> countryFileParsers = new ArrayList<>();
        for (String filePath : filePaths) {
            countryFileParsers.add(new AsyncCountryParser(filePath, dataSourceType));
        }
        return countryFileParsers;
    }

    private List<Country> asyncParseFilesAndWaitForResult(List<AsyncCountryParser> parsers) throws Exception {
        for (AsyncCountryParser parser : parsers) {
            parser.asyncParseCountries();
        }

        List<Country> countriesToPersist = new ArrayList<>();
        for (AsyncCountryParser parser : parsers) {
            parser.blockUntilJobIsFinished();
            if (parser.getParseException() != null) {
                throw new ParseXmlException(PARSE_XML_EXCEPTION.getDescription(), parser.getParseException(), PARSE_XML_EXCEPTION.getCode());
            }
            countriesToPersist.addAll(parser.getCountries());
        }
        return countriesToPersist;
    }
}
