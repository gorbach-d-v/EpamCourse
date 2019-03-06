package ru.gorbach.hw9.city.repo;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.common.business.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo {

    void add(City city);

    void update(City city);

    City findById(long id);

    List<City> search(CitySearchCondition searchCondition);
}
