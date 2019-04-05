package ru.gorbach.hw17.city.service;

import ru.gorbach.hw17.city.domain.City;
import ru.gorbach.hw17.city.search.CitySearchCondition;
import ru.gorbach.hw17.common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition);

}
