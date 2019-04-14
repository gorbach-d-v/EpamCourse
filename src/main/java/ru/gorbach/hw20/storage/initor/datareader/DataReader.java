package ru.gorbach.hw20.storage.initor.datareader;

import ru.gorbach.hw20.country.domain.Country;

import java.util.List;

@FunctionalInterface
public interface DataReader {
    List<Country> getDataFromFile(String file) throws Exception;
}
