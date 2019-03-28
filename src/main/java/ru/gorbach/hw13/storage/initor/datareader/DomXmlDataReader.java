package ru.gorbach.hw13.storage.initor.datareader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.gorbach.hw13.city.domain.City;
import ru.gorbach.hw13.country.domain.*;
import ru.gorbach.hw13.storage.initor.exception.checked.InitDataCheckedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.gorbach.hw13.storage.initor.exception.InitDataExceptionMeta.PARSE_COUNTRY_DISCRIMINATOR_ERROR;

public class DomXmlDataReader implements DataReader {
    public List<Country> getDataFromFile(String file) throws Exception {

        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("No such file");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(file));

        Node root = doc.getElementsByTagName("countries").item(0);
        NodeList countries = ((Element) root).getElementsByTagName("country");

        List<Country> parsedCountries = new ArrayList<>();

        if (countries.getLength() > 0) {
            for (int i = 0; i < countries.getLength(); i++) {

                Element xmlCountry = (Element) countries.item(i);
                Country country = createCountryByDiscriminator((xmlCountry).getAttribute("type"));
                parsedCountries.add(country);

                country.setName(xmlCountry.getElementsByTagName("name").item(0).getTextContent());
                country.setLanguage(xmlCountry.getElementsByTagName("language").item(0).getTextContent());

                Node citiesRoot = xmlCountry.getElementsByTagName("cities").item(0);
                if (((Element) citiesRoot).getElementsByTagName("city") != null) {
                    NodeList xmlCities = ((Element) citiesRoot).getElementsByTagName("city");

                    country.setCities(new ArrayList<>());
                    for (int k = 0; k < xmlCities.getLength(); k++) {
                        Element xmlCity = (Element) xmlCities.item(k);

                        City city = new City();
                        country.getCities().add(city);

                        city.setName(xmlCity.getElementsByTagName("name").item(0).getTextContent());
                        city.setPopulation(Integer.parseInt(xmlCity.getElementsByTagName("population").item(0).getTextContent()));
                        city.setCapital(Boolean.parseBoolean(xmlCity.getElementsByTagName("isCapital").item(0).getTextContent()));
                    }
                }

                if (CountryWithHotClimate.class.equals(country.getClass())) {
                    appendHotCountryAttributes((CountryWithHotClimate) country, xmlCountry);
                } else if (CountryWithColdClimate.class.equals(country.getClass())) {
                    appendColdCountryAttributes((CountryWithColdClimate) country, xmlCountry);
                }


            }
        }

        return parsedCountries;
    }

    private Country createCountryByDiscriminator(String discriminatorAsStr) throws InitDataCheckedException {
        if (CountryDiscriminator.isDiscriminatorNotExists(discriminatorAsStr)) {
            throw new InitDataCheckedException(
                    PARSE_COUNTRY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(discriminatorAsStr),
                    PARSE_COUNTRY_DISCRIMINATOR_ERROR.getCode()
            );
        } else {
            CountryDiscriminator discriminator = CountryDiscriminator.getDiscriminatorByName(discriminatorAsStr);
            if (CountryDiscriminator.HOT.equals(discriminator)) {
                return new CountryWithHotClimate();
            }
            return new CountryWithColdClimate();
        }
    }

    private void appendHotCountryAttributes(CountryWithHotClimate country, Element xmlCountry) {
        String data = xmlCountry.getElementsByTagName("hotMonth").item(0).getTextContent();
        if (Month.isStrBelongsToEnumValues(data)) {
            country.setHottestMonth(Month.valueOf(data));
        }
        country.setAverageTemp(Double.parseDouble(xmlCountry.getElementsByTagName("avgTemp").item(0).getTextContent()));
    }

    private void appendColdCountryAttributes(CountryWithColdClimate country, Element xmlCountry) {
        country.setTelephoneCode(Integer.parseInt(xmlCountry.getElementsByTagName("phoneCode").item(0).getTextContent()));
        country.setPolarNight(Boolean.parseBoolean(xmlCountry.getElementsByTagName("havePolarNight").item(0).getTextContent()));
    }
}
