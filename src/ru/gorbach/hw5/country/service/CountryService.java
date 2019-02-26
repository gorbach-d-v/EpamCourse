package ru.gorbach.hw5.country.service;

import ru.gorbach.hw5.common.business.service.BaseService;
import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.country.search.CountrySearchCondition;

public interface CountryService extends BaseService {

    void add(Country country);

    Country findById(long id);

    Country[] search(CountrySearchCondition searchCondition);
}
