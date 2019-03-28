package ru.gorbach.hw11.country.repo;

import ru.gorbach.hw11.common.solutions.repo.BaseRepo;
import ru.gorbach.hw11.country.domain.Country;
import ru.gorbach.hw11.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);
}
