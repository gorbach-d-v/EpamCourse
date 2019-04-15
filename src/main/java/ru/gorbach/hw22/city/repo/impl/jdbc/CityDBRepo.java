package ru.gorbach.hw22.city.repo.impl.jdbc;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.city.repo.CityRepo;
import ru.gorbach.hw22.city.search.CitySearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CityDBRepo implements CityRepo {
    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        return null;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public void add(Collection<City> t) {

    }

    @Override
    public void update(City city) {

    }

    @Override
    public Optional<City> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
