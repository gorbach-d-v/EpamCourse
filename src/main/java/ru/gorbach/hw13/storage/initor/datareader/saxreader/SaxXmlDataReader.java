package ru.gorbach.hw13.storage.initor.datareader.saxreader;

import ru.gorbach.hw13.country.domain.Country;
import ru.gorbach.hw13.storage.initor.datareader.DataReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class SaxXmlDataReader implements DataReader {

    @Override
    public List<Country> getDataFromFile(String file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        CountriesSaxHandler handler = new CountriesSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getCountries();
    }
}
