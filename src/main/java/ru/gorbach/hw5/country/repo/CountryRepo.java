package ru.gorbach.hw5.country.repo;

import ru.gorbach.hw5.common.business.repo.BaseRepo;
import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {

    void add(Country country);

    Country findById(long id);

    Country[] search(CountrySearchCondition searchCondition);
}
