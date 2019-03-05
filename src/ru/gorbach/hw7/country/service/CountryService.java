package ru.gorbach.hw7.country.service;

import ru.gorbach.hw7.country.domain.Country;
import ru.gorbach.hw7.country.search.CountrySearchCondition;
import ru.gorbach.hw7.common.business.service.BaseService;

import java.util.List;

public interface CountryService extends BaseService {

    void add(Country country);

    void update(Country country);

    Country findById(Long id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);
}
