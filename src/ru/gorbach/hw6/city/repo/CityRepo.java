package ru.gorbach.hw6.city.repo;

import ru.gorbach.hw6.common.business.repo.BaseRepo;
import ru.gorbach.hw6.city.domain.City;
import ru.gorbach.hw6.city.search.CitySearchCondition;

import java.util.List;

public interface CityRepo extends BaseRepo {

    void add(City city);

    City findById(long id);

    List<City> search(CitySearchCondition searchCondition);
}
