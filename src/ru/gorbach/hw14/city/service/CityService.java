package ru.gorbach.hw14.city.service;

import ru.gorbach.hw14.city.search.CitySearchCondition;
import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.common.solutions.service.BaseService;
import ru.gorbach.hw14.city.domain.City;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition, Pagination pagination);

}
