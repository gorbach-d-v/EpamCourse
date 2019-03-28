package ru.gorbach.hw16.country.service;

import ru.gorbach.hw16.common.solutions.service.BaseService;
import ru.gorbach.hw16.country.domain.Country;
import ru.gorbach.hw16.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);

}
