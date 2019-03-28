package ru.gorbach.hw12.city.repo;

import ru.gorbach.hw12.city.domain.City;
import ru.gorbach.hw12.city.search.CitySearchCondition;
import ru.gorbach.hw12.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);
}
