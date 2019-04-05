package ru.gorbach.hw17.country.service;

import ru.gorbach.hw17.common.solutions.service.BaseService;
import ru.gorbach.hw17.country.domain.Country;
import ru.gorbach.hw17.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);

}
