package ru.gorbach.hw19.country.service;

import ru.gorbach.hw19.common.solutions.service.BaseService;
import ru.gorbach.hw19.country.domain.Country;
import ru.gorbach.hw19.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);

}
