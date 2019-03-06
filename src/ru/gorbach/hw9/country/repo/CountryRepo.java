package ru.gorbach.hw9.country.repo;

import ru.gorbach.hw9.common.business.repo.BaseRepo;
import ru.gorbach.hw9.country.domain.Country;
import ru.gorbach.hw9.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo {

    void add(Country country);

    void update(Country country);

    Country findById(long id);

    List<Country> search(CountrySearchCondition searchCondition);
}
