package ru.gorbach.hw9.city.service;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition);

}
