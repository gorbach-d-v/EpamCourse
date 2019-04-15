package ru.gorbach.hw22.storage.initor.datareader;

import ru.gorbach.hw22.country.domain.Country;

import java.util.List;

@FunctionalInterface
public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
