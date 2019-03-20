package ru.gorbach.hw12.storage.initor.datareader;

import ru.gorbach.hw12.country.domain.Country;

import java.util.List;

public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
