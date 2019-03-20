package ru.gorbach.hw11.storage.initor;

import ru.gorbach.hw11.country.domain.Country;
import ru.gorbach.hw11.country.service.CountryService;

import java.util.List;

public class StorageInitor {
    private CountryService countryService;

    public StorageInitor(CountryService countryService) {
        this.countryService = countryService;
    }

    public void initStorageWithCountriesAndCities(String filePath) throws Exception {
        List<Country> countryList = getMarksFromStorage(filePath);

        if (!countryList.isEmpty()) {
            for (Country country : countryList) {
                countryService.add(country);
            }
        }
    }

    private List<Country> getMarksFromStorage(String filePath) throws Exception {

        TxtInitialization txtInitialization = new TxtInitialization();

        return txtInitialization.getDataFromFile(filePath);
    }
}
