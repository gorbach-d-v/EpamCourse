package ru.gorbach.hw20.city.repo;

import ru.gorbach.hw20.city.domain.City;
import ru.gorbach.hw20.city.search.CitySearchCondition;
import ru.gorbach.hw20.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);
}
