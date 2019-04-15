package ru.gorbach.hw22.storage.initor.datareader.staxreader;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.common.solutions.xml.stax.parse.CustomStaxReader;
import ru.gorbach.hw22.country.domain.*;
import ru.gorbach.hw22.storage.initor.datareader.DataReader;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.gorbach.hw22.common.solutions.xml.stax.XmlStaxUtils.readContent;

public class StaxXmlDataReader implements DataReader {
    @Override
    public List<Country> getDataFromFile(String file) throws Exception {
        try (CustomStaxReader customStaxReader = CustomStaxReader.newInstance(file)) {
            return readDocument(customStaxReader.getReader());
        }
    }

    private List<Country> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();

                    if ("countries".equals(elementName)) {
                        return readCountries(reader);
                    }
                    break;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private List<Country> readCountries(XMLStreamReader reader) throws XMLStreamException {
        List<Country> countries = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();

                    if ("country".equals(elementName)) {
                        countries.add(readCountry(reader));
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    return countries;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private Country readCountry(XMLStreamReader reader) throws XMLStreamException {

        Country country = createCountryByDiscriminator(reader.getAttributeValue(null, "type"));
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    if ("name".equals(elementName)) {
                        country.setName(readContent(reader));
                    } else if ("language".equals(elementName)) {
                        country.setLanguage(readContent(reader));
                    } else if ("hotMonth".equals(elementName)) {
                        String data = readContent(reader);
                        if (Month.isStrBelongsToEnumValues(data)) {
                            ((CountryWithHotClimate) country).setHottestMonth(Month.valueOf(data));
                        }
                    } else if ("avgTemp".equals(elementName)) {
                        ((CountryWithHotClimate) country).setAverageTemp(Double.parseDouble(readContent(reader)));
                    } else if ("phoneCode".equals(elementName)) {
                        ((CountryWithColdClimate) country).setTelephoneCode(Integer.parseInt(readContent(reader)));
                    } else if ("havePolarNight".equals(elementName)) {
                        ((CountryWithColdClimate) country).setPolarNight(Boolean.parseBoolean(readContent(reader)));
                    } else if ("cities".equals(elementName)) {
                        country.setCities(readCities(reader));
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    return country;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private List<City> readCities(XMLStreamReader reader) throws XMLStreamException {
        List<City> cities = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    if ("city".equals(elementName)) {
                        cities.add(readCity(reader));
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    return cities;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private City readCity(XMLStreamReader reader) throws XMLStreamException {
        City city = new City();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String elementName = reader.getLocalName();
                    if ("name".equals(elementName)) {
                        city.setName(readContent(reader));
                    } else if ("population".equals(elementName)) {
                        city.setPopulation(Integer.parseInt(readContent(reader)));
                    } else if ("isCapital".equals(elementName)) {
                        city.setCapital(Boolean.parseBoolean(readContent(reader)));
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    return city;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private Country createCountryByDiscriminator(String discriminatorAsStr) {

        CountryDiscriminator discriminator = CountryDiscriminator.getDiscriminatorByName(discriminatorAsStr).get();
        if (CountryDiscriminator.HOT.equals(discriminator)) {
            return new CountryWithHotClimate();
        }
        return new CountryWithColdClimate();
    }
}
