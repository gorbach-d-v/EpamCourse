package ru.gorbach.hw19.city.repo;

import ru.gorbach.hw19.city.domain.City;
import ru.gorbach.hw19.common.solutions.repo.BaseRepo;
import ru.gorbach.hw19.city.search.CitySearchCondition;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);
}
