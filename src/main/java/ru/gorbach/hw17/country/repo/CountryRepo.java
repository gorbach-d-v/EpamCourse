package ru.gorbach.hw17.country.repo;

import ru.gorbach.hw17.common.solutions.repo.BaseRepo;
import ru.gorbach.hw17.country.domain.Country;
import ru.gorbach.hw17.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);
}
