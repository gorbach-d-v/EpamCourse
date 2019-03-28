package ru.gorbach.hw14.storage.initor.datareader;

import ru.gorbach.hw14.country.domain.Country;

import java.util.List;

public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
