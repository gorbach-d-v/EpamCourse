package ru.gorbach.hw6.country.repo;

import ru.gorbach.hw6.common.business.repo.BaseRepo;
import ru.gorbach.hw6.country.domain.Country;
import ru.gorbach.hw6.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo {

    void add(Country country);

    Country findById(long id);

    List<Country> search(CountrySearchCondition searchCondition);
}
