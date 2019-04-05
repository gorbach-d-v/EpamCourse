package ru.gorbach.hw17.storage.initor.datareader;

import ru.gorbach.hw17.country.domain.Country;

import java.util.List;

@FunctionalInterface
public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
