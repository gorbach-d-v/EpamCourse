package ru.gorbach.hw20.storage.initor;

import ru.gorbach.hw20.country.domain.Country;
import ru.gorbach.hw20.storage.initor.datareader.DataReader;
import ru.gorbach.hw20.storage.initor.datareader.DomXmlDataReader;
import ru.gorbach.hw20.storage.initor.datareader.TxtDataReader;
import ru.gorbach.hw20.storage.initor.datareader.saxreader.SaxXmlDataReader;
import ru.gorbach.hw20.storage.initor.datareader.staxreader.StaxXmlDataReader;

import java.util.ArrayList;
import java.util.List;

public class AsyncCountryParser implements Runnable{
    private String filePath;
    private StorageInitor.DataSourceType dataSourceType;
    private Thread thread;
    private List<Country> countries;
    private volatile Exception parseException;

    public AsyncCountryParser(String filePath, StorageInitor.DataSourceType dataSourceType) {
        this.filePath = filePath;
        this.dataSourceType = dataSourceType;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            countries = getCountriesFromStorage(filePath, dataSourceType);
        } catch (Exception e) {
            System.out.println("Error while parse file with countries");
            parseException = e;
        }
    }

    public synchronized List<Country> getCountries() {
        return countries;
    }

    public void asyncParseCountries() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }


    private List<Country> getCountriesFromStorage(String filePath, StorageInitor.DataSourceType dataSourceType) throws Exception {

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

    public Exception getParseException() {
        return parseException;
    }
}
