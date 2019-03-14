package ru.gorbach.hw8.country.repo;

import ru.gorbach.hw8.common.business.repo.BaseRepo;
import ru.gorbach.hw8.country.domain.Country;
import ru.gorbach.hw8.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo {

    void add(Country country);

    void update(Country country);

    Country findById(long id);

    List<Country> search(CountrySearchCondition searchCondition);
}
