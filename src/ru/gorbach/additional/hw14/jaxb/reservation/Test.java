package ru.gorbach.additional.hw14.jaxb.reservation;

import ru.gorbach.additional.hw14.jaxb.reservation.city.City;
import ru.gorbach.additional.hw14.jaxb.reservation.country.Country;
import ru.gorbach.additional.hw14.jaxb.reservation.country.CountryWithColdClimate;
import ru.gorbach.additional.hw14.jaxb.reservation.country.CountryWithHotClimate;
import ru.gorbach.additional.hw14.jaxb.reservation.country.Month;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final String FILE_PATH = "./src/ru/gorbach/additional/hw14/jaxb/reservation/countries.xml";

    private static class Application {
        private static int cityIndex = 0;

        private List<Country> createCountries() {
            List<Country> countries = new ArrayList<>();
            Country country1 = new CountryWithHotClimate("USA", "English");
            ((CountryWithHotClimate) country1).setAverageTemp(15.4);
            ((CountryWithHotClimate) country1).setHottestMonth(Month.SEPTEMBER);
            country1.setCities(createCities());
            Country country2 = new CountryWithColdClimate("Russia", "Russian");
            ((CountryWithColdClimate) country2).setPolarNight(true);
            ((CountryWithColdClimate) country2).setTelephoneCode(7);
            country2.setCities(createCities());
            Country country3 = new CountryWithHotClimate("Spain", "Spanish");
            ((CountryWithHotClimate) country3).setAverageTemp(18.2);
            ((CountryWithHotClimate) country3).setHottestMonth(Month.JULY);
            country3.setCities(createCities());

            countries.add(country1);
            countries.add(country2);
            countries.add(country3);

            return countries;
        }

        private List<City> createCities() {
            List<City> cities = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                City city = new City("City " + cityIndex, 100 * cityIndex, false);
                cityIndex++;
                cities.add(city);
            }
            return cities;
        }

        private Root getRoot() {
            Root root = new Root();
            return root;
        }

        private void marshallCountries(File file) throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            if (file != null) {
                marshaller.marshal(getRoot(), file);
            } else {
                marshaller.marshal(getRoot(), System.out);
            }
        }

        private Root unMarshallCountries(File file) throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Root) unmarshaller.unmarshal(file);
        }
    }

    @XmlType
    @XmlRootElement(name = "countries")
    public static class Root {
        Application application = new Application();
        @XmlElements({
                @XmlElement(name = "coldCountry", type = CountryWithColdClimate.class),
                @XmlElement(name = "hotCountry", type = CountryWithHotClimate.class)
        })
        private List<Country> countries = application.createCountries();

        @Override
        public String toString() {
            return "Root{" +
                    "countries=" + countries +
                    '}';
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        try {
            File file = new File(FILE_PATH);
            application.marshallCountries(file);
            System.out.println(application.unMarshallCountries(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
