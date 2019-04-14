package ru.gorbach.hw19.city.service;

import ru.gorbach.hw19.city.domain.City;
import ru.gorbach.hw19.common.solutions.service.BaseService;
import ru.gorbach.hw19.city.search.CitySearchCondition;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition);

}
