package ru.gorbach.hw16.storage.initor.datareader;

import ru.gorbach.hw16.country.domain.Country;

import java.util.List;

public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
