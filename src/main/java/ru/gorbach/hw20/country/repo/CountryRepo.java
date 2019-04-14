package ru.gorbach.hw20.country.repo;

import ru.gorbach.hw20.common.solutions.repo.BaseRepo;
import ru.gorbach.hw20.country.domain.Country;
import ru.gorbach.hw20.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);
}
