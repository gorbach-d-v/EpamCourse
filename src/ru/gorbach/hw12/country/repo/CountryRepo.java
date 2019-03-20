package ru.gorbach.hw12.country.repo;

import ru.gorbach.hw12.common.solutions.repo.BaseRepo;
import ru.gorbach.hw12.country.domain.Country;
import ru.gorbach.hw12.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);
}
