package ru.gorbach.hw12.country.service;

import ru.gorbach.hw12.common.solutions.service.BaseService;
import ru.gorbach.hw12.country.domain.Country;
import ru.gorbach.hw12.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);

}
