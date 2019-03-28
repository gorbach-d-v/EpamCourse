package ru.gorbach.hw5.city.repo;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.city.search.CitySearchCondition;
import ru.gorbach.hw5.common.business.repo.BaseRepo;

public interface CityRepo extends BaseRepo {

    void add(City city);

    City findById(long id);

    City[] search(CitySearchCondition searchCondition);
}
