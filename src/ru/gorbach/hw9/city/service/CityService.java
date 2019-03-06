package ru.gorbach.hw9.city.service;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {

    void add(City city);

    void update(City city);

    City findById(Long id);

    void delete(City city);

    List<City> search(CitySearchCondition searchCondition);
}
