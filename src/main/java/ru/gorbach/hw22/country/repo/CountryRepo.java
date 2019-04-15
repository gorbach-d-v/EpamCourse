package ru.gorbach.hw22.country.repo;

import ru.gorbach.hw22.common.solutions.repo.BaseRepo;
import ru.gorbach.hw22.country.domain.Country;
import ru.gorbach.hw22.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<? extends Country> search(CountrySearchCondition searchCondition);
}
