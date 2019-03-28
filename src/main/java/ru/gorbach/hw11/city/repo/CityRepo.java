package ru.gorbach.hw11.city.repo;

import ru.gorbach.hw11.city.domain.City;
import ru.gorbach.hw11.city.search.CitySearchCondition;
import ru.gorbach.hw11.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);
}
