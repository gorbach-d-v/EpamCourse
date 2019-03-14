package ru.gorbach.hw9.city.repo;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City,Long> {
    List<City> search(CitySearchCondition searchCondition);
}
