package ru.gorbach.hw16.city.repo;

import ru.gorbach.hw16.city.domain.City;
import ru.gorbach.hw16.city.search.CitySearchCondition;
import ru.gorbach.hw16.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);
}
