package ru.gorbach.hw17.storage.initor.datareader.saxreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gorbach.hw17.city.domain.City;
import ru.gorbach.hw17.country.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CountriesSaxHandler extends DefaultHandler {
    private List<Country> countries;
    private Country country;
    private List<City> cities;
    private City city;
    private StringBuilder stringBuilder = new StringBuilder();

    Stack<String> path = new Stack<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        path.push(qName);

        switch (qName) {

            case "countries": {
                countries = new ArrayList<>();
                break;
            }

            case "country": {
                String discriminator = attributes.getValue("type");
                country = createCountryByDiscriminator(discriminator);
                break;
            }

            case "cities": {
                cities = new ArrayList<>();
                break;
            }

            case "city": {
                city = new City();
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        path.pop();
        switch (qName) {
            case "country": {
                countries.add(country);
                break;
            }

            case "name": {
                String data = stringBuilder.toString();
                String parent = path.peek();
                if ("country".equals(parent)){
                    country.setName(data);
                } else if ("city".equals(parent)) {
                    city.setName(data);
                }
                stringBuilder.setLength(0);
                break;
            }

            case "language": {
                country.setLanguage(stringBuilder.toString());
                stringBuilder.setLength(0);
                break;
            }

            case "hotMonth": {
                String data = stringBuilder.toString();
                if (Month.isStrBelongsToEnumValues(data)) {
                    ((CountryWithHotClimate) country).setHottestMonth(Month.valueOf(data));
                }
                stringBuilder.setLength(0);
                break;
            }

            case "avgTemp": {
                ((CountryWithHotClimate) country).setAverageTemp(Double.parseDouble(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "phoneCode": {
                ((CountryWithColdClimate) country).setTelephoneCode(Integer.parseInt(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "havePolarNight": {
                ((CountryWithColdClimate) country).setPolarNight(Boolean.parseBoolean(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "population": {
                city.setPopulation(Integer.parseInt(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "isCapital": {
                city.setCapital(Boolean.parseBoolean(stringBuilder.toString()));
                stringBuilder.setLength(0);
                break;
            }

            case "city": {
                cities.add(city);
                break;
            }

            case "cities": {
                country.setCities(cities);
                break;
            }


        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        stringBuilder.append(data.replaceAll("\\n",""));
    }

    public List<Country> getCountries() {
        return countries;
    }

    private Country createCountryByDiscriminator(String discriminatorAsStr) {

        CountryDiscriminator discriminator = CountryDiscriminator.getDiscriminatorByName(discriminatorAsStr);
        if (CountryDiscriminator.HOT.equals(discriminator)) {
            return new CountryWithHotClimate();
        }
        return new CountryWithColdClimate();
    }
}
