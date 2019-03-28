package ru.gorbach.hw16.country.repo;

import ru.gorbach.hw16.common.solutions.repo.BaseRepo;
import ru.gorbach.hw16.country.domain.Country;
import ru.gorbach.hw16.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);
}
