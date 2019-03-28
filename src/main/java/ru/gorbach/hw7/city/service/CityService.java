package ru.gorbach.hw7.city.service;

import ru.gorbach.hw7.city.domain.City;
import ru.gorbach.hw7.city.search.CitySearchCondition;
import ru.gorbach.hw7.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {

    void add(City city);

    void update(City city);

    City findById(Long id);

    void delete(City city);

    List<City> search(CitySearchCondition searchCondition);
}
