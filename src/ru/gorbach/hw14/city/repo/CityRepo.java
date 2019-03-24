package ru.gorbach.hw14.city.repo;

import ru.gorbach.hw14.city.domain.City;
import ru.gorbach.hw14.city.search.CitySearchCondition;
import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition, Pagination pagination);
}
