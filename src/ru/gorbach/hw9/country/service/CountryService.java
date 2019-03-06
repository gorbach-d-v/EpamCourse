package ru.gorbach.hw9.country.service;

import ru.gorbach.hw9.country.domain.Country;
import ru.gorbach.hw9.country.search.CountrySearchCondition;
import ru.gorbach.hw9.common.business.service.BaseService;

import java.util.List;

public interface CountryService extends BaseService {

    void add(Country country);

    void update(Country country);

    Country findById(Long id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);
}
