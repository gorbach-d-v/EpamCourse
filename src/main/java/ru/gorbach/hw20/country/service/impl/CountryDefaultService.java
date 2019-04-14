package ru.gorbach.hw20.country.service.impl;

import ru.gorbach.hw20.city.domain.City;
import ru.gorbach.hw20.city.service.CityService;
import ru.gorbach.hw20.common.business.exception.ReservationUncheckedException;
import ru.gorbach.hw20.country.domain.Country;
import ru.gorbach.hw20.country.exception.CountryExceptionMeta;
import ru.gorbach.hw20.country.exception.unchecked.DeleteCountryException;
import ru.gorbach.hw20.country.search.CountrySearchCondition;
import ru.gorbach.hw20.country.repo.CountryRepo;
import ru.gorbach.hw20.country.service.CountryService;
import ru.gorbach.hw20.order.repo.OrderRepo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CountryDefaultService implements CountryService {
    private CountryRepo countryRepo;
    private CityService cityService;
    private OrderRepo orderRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityService cityService, OrderRepo orderRepo) {
        this.countryRepo = countryRepo;
        this.cityService = cityService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Country add(Country country) {
        if (country != null) {
            countryRepo.add(country);
            if (country.getCities() != null && !country.getCities().isEmpty()) {
                for (City city : country.getCities()) {
                    city.setCountryId(country.getId());
                    cityService.add(city);
                }
            }

        }
        return country;
    }

    @Override
    public void add(Collection<Country> countriesList) {
        if (countriesList != null) {
            for (Country country : countriesList) {
                add(country);
            }
        }
    }

    @Override
    public void update(Country country) {
        if (country != null) {
            if (country.getId() != null) {
                countryRepo.update(country);
            }
        }
    }

    @Override
    public Optional<Country> findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
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
        if (country != null) {
            if (country.getId() != null) {
                countryRepo.deleteById(country.getId());
            }
        }
    }

    @Override
    public void delete(Collection<Country> countriesList) {
        if (countriesList != null) {
            for (Country country : countriesList) {
                delete(country);
            }
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    @Override
    public int countAll() {
        return countryRepo.countAll();
    }


    private void removeAllCitiesFromCountry(Long countryId) throws ReservationUncheckedException {
        findById(countryId).ifPresent(country -> {
            if (country.getCities() != null) {
                country.getCities().forEach(city -> cityService.deleteById(city.getId()));
            }
        });
    }
}
