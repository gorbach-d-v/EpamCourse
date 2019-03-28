package ru.gorbach.hw13.storage.initor.datareader;

import ru.gorbach.hw13.country.domain.Country;

import java.util.List;

public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
