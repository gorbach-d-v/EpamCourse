package ru.gorbach.hw20.storage.initor.datareader.saxreader;

import ru.gorbach.hw20.country.domain.Country;
import ru.gorbach.hw20.storage.initor.datareader.DataReader;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

import static ru.gorbach.hw20.common.solutions.xml.sax.XmlSaxUtils.getParser;

public class SaxXmlDataReader implements DataReader {

    @Override
    public List<Country> getDataFromFile(String file) throws Exception {
        SAXParser saxParser = getParser();

        CountriesSaxHandler handler = new CountriesSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getCountries();
    }
}
