package ru.gorbach.hw13.city.service;

import ru.gorbach.hw13.city.domain.City;
import ru.gorbach.hw13.city.search.CitySearchCondition;
import ru.gorbach.hw13.common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition);

}
