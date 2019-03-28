package ru.gorbach.hw16.storage.initor.datareader.saxreader;

import ru.gorbach.hw16.country.domain.Country;
import ru.gorbach.hw16.storage.initor.datareader.DataReader;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

import static ru.gorbach.hw16.common.solutions.xml.sax.XmlSaxUtils.getParser;

public class SaxXmlDataReader implements DataReader {

    @Override
    public List<Country> getDataFromFile(String file) throws Exception {
        SAXParser saxParser = getParser();

        CountriesSaxHandler handler = new CountriesSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getCountries();
    }
}
