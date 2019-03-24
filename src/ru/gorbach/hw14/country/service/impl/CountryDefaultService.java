package ru.gorbach.hw14.country.service.impl;

import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.country.exception.unchecked.DeleteCountryException;
import ru.gorbach.hw14.country.repo.CountryRepo;
import ru.gorbach.hw14.country.search.CountrySearchCondition;
import ru.gorbach.hw14.country.service.CountryService;
import ru.gorbach.hw14.city.domain.City;
import ru.gorbach.hw14.city.repo.CityRepo;
import ru.gorbach.hw14.common.business.exception.ReservationUncheckedException;
import ru.gorbach.hw14.country.domain.Country;
import ru.gorbach.hw14.country.exception.CountryExceptionMeta;
import ru.gorbach.hw14.order.repo.OrderRepo;

import java.util.Collections;
import java.util.List;

public class CountryDefaultService implements CountryService {
    private CountryRepo countryRepo;
    private CityRepo cityRepo;
    private OrderRepo orderRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo, OrderRepo orderRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Country country) {
        if (country != null) {
            countryRepo.add(country);
            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityRepo.add(city);
                    }
                }
            }
        }
    }

    @Override
    public void update(Country country) {
        if (country.getId() != null) {
            countryRepo.update(country);
        }
    }


    @Override
    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition, Pagination pagination) {
        return countryRepo.search(searchCondition, pagination);
    }

    @Override
    public void deleteById(Long id) throws ReservationUncheckedException {
        if (id != null) {
            boolean noOrders = orderRepo.countByCountry(id) == 0;

            if (!noOrders) {
                throw new DeleteCountryException(CountryExceptionMeta.DELETE_COUNTRY_CONSTRAINT_ERROR);
            }
            removeAllCitiesFromCountry(id);
            countryRepo.deleteById(id);
        }
    }

    @Override
    public void delete(Country country) {
        if (country.getId() != null) {
            countryRepo.deleteById(country.getId());
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }

    private void removeAllCitiesFromCountry(Long id) throws ReservationUncheckedException {
        Country country = findById(id);
        if (country != null) {
            List<City> cities = country.getCities() == null ? Collections.emptyList() : country.getCities();

            for (City city : cities) {
                cityRepo.deleteById(city.getId());
            }
        }
    }
}
