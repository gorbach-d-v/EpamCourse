package ru.gorbach.hw19.storage.initor.datareader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.gorbach.hw19.city.domain.City;
import ru.gorbach.hw19.country.domain.*;
import ru.gorbach.hw19.storage.initor.exception.checked.InitDataCheckedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.gorbach.hw19.common.solutions.xml.dom.XmlDomUtils.*;
import static ru.gorbach.hw19.storage.initor.exception.InitDataExceptionMeta.PARSE_COUNTRY_DISCRIMINATOR_ERROR;

public class DomXmlDataReader implements DataReader {
    public List<Country> getDataFromFile(String file) throws Exception {

        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("No such file");
        }

        Document doc = getDocument(file);

        Element root = getOnlyElement(doc,"countries");
        NodeList countries = root.getElementsByTagName("country");

        List<Country> parsedCountries = new ArrayList<>();

        if (countries.getLength() > 0) {
            for (int i = 0; i < countries.getLength(); i++) {

                Element xmlCountry = (Element) countries.item(i);
                Country country = createCountryByDiscriminator((xmlCountry).getAttribute("type"));
                parsedCountries.add(country);

                country.setName(getOnlyElementTextContent(xmlCountry,"name"));
                country.setLanguage(getOnlyElementTextContent(xmlCountry,"language"));

                Element citiesRoot = getOnlyElement(xmlCountry,"cities");
                if (citiesRoot.getElementsByTagName("city") != null) {
                    NodeList xmlCities = citiesRoot.getElementsByTagName("city");

                    country.setCities(new ArrayList<>());
                    for (int k = 0; k < xmlCities.getLength(); k++) {
                        Element xmlCity = (Element) xmlCities.item(k);

                        City city = new City();
                        country.getCities().add(city);

                        city.setName(getOnlyElementTextContent(xmlCity,"name"));
                        city.setPopulation(Integer.parseInt(getOnlyElementTextContent(xmlCity,"population")));
                        city.setCapital(Boolean.parseBoolean(getOnlyElementTextContent(xmlCity,"isCapital")));
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
        String data = getOnlyElementTextContent(xmlCountry,"hotMonth");
        if (Month.isStrBelongsToEnumValues(data)) {
            country.setHottestMonth(Month.valueOf(data));
        }
        country.setAverageTemp(Double.parseDouble(getOnlyElementTextContent(xmlCountry,"avgTemp")));
    }

    private void appendColdCountryAttributes(CountryWithColdClimate country, Element xmlCountry) {
        country.setTelephoneCode(Integer.parseInt(getOnlyElementTextContent(xmlCountry,"phoneCode")));
        country.setPolarNight(Boolean.parseBoolean(getOnlyElementTextContent(xmlCountry,"havePolarNight")));
    }
}
