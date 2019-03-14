package ru.gorbach.hw8.city.service;

import ru.gorbach.hw8.city.domain.City;
import ru.gorbach.hw8.city.search.CitySearchCondition;
import ru.gorbach.hw8.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {

    void add(City city);

    void update(City city);

    City findById(Long id);

    void delete(City city);

    List<City> search(CitySearchCondition searchCondition);
}
