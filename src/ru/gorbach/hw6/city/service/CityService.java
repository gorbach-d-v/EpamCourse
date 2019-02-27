package ru.gorbach.hw6.city.service;

import ru.gorbach.hw6.city.domain.City;
import ru.gorbach.hw6.city.search.CitySearchCondition;
import ru.gorbach.hw6.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {

    void add(City city);

    City findById(Long id);

    void delete (City city);

    List<City> search(CitySearchCondition searchCondition);
}
