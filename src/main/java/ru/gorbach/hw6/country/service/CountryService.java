package ru.gorbach.hw6.country.service;

import ru.gorbach.hw6.country.domain.Country;
import ru.gorbach.hw6.country.search.CountrySearchCondition;
import ru.gorbach.hw6.common.business.service.BaseService;

import java.util.List;

public interface CountryService extends BaseService {

    void add(Country country);

    Country findById(Long id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);
}
