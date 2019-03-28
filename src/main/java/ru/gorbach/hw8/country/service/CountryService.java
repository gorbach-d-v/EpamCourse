package ru.gorbach.hw8.country.service;

import ru.gorbach.hw8.common.business.service.BaseService;
import ru.gorbach.hw8.country.domain.Country;
import ru.gorbach.hw8.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService {

    void add(Country country);

    void update(Country country);

    Country findById(Long id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);
}
