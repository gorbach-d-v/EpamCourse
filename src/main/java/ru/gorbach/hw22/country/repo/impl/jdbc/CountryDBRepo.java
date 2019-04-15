package ru.gorbach.hw22.country.repo.impl.jdbc;

import ru.gorbach.hw22.country.domain.Country;
import ru.gorbach.hw22.country.repo.CountryRepo;
import ru.gorbach.hw22.country.search.CountrySearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CountryDBRepo implements CountryRepo {
    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        return null;
    }

    @Override
    public Country add(Country country) {
        return null;
    }

    @Override
    public void add(Collection<Country> t) {

    }

    @Override
    public void update(Country country) {

    }

    @Override
    public Optional<Country> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<Country> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
