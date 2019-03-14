package ru.gorbach.hw8.city.repo;

import ru.gorbach.hw8.city.domain.City;
import ru.gorbach.hw8.city.search.CitySearchCondition;
import ru.gorbach.hw8.common.business.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo {

    void add(City city);

    void update(City city);

    City findById(long id);

    List<City> search(CitySearchCondition searchCondition);
}
