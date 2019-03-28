package ru.gorbach.hw5.city.service;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.city.search.CitySearchCondition;
import ru.gorbach.hw5.common.business.service.BaseService;

public interface CityService extends BaseService {

    void add(City city);

    City findById(long id);

    City[] search(CitySearchCondition searchCondition);
}
