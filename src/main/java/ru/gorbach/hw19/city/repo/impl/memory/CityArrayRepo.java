package ru.gorbach.hw19.city.repo.impl.memory;

import ru.gorbach.hw19.city.domain.City;
import ru.gorbach.hw19.common.business.search.Paginator;
import ru.gorbach.hw19.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw19.city.repo.CityRepo;
import ru.gorbach.hw19.city.search.CitySearchCondition;
import ru.gorbach.hw19.common.solutions.utils.ArrayUtils;
import ru.gorbach.hw19.storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static ru.gorbach.hw19.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw19.storage.Storage.cities;


public class CityArrayRepo implements CityRepo {
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();
    private int cityIndex = 0;

    @Override
    public City add(City city) {
        if (cityIndex == cities.length) {
            City[] newArrCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newArrCities, 0, cities.length);
            cities = newArrCities;
        }

        city.setId(SequenceGenerator.generateId());
        cities[cityIndex] = city;
        cityIndex++;
        return city;
    }

    @Override
    public void add(Collection<City> citiesList) {
        for (City city : citiesList){
            add(city);
        }
    }

    @Override
    public void update(City city) {
        // nothing to update
    }

    @Override
    public Optional<City> findById(Long id) {
        return findCityIndexById(id).map(index->cities[index]);
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            Optional<City> optionalCountry = findById(searchCondition.getId());
            if (optionalCountry.isPresent()) {
                return Collections.singletonList(optionalCountry.get());
            } else {
                return Collections.emptyList();
            }
        } else {
            List<City> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            if (!result.isEmpty() && searchCondition.needPagination()) {
                result = getPageOfData(result, searchCondition.getPaginator());
            }
            return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        findCityIndexById(id).ifPresent(this::deleteCityByIndex);
    }

    @Override
    public void printAll() {
        for (City city : cities) {
            System.out.println(city);
        }
    }

    @Override
    public List<City> findAll() {
        return new ArrayList<>(Arrays.asList(cities));
    }

    @Override
    public int countAll() {
        return cities.length;
    }


    private List<City> doSearch(CitySearchCondition searchCondition) {
        boolean searchByName = isNotBlank(searchCondition.getName());
        boolean searchByPopulation = (searchCondition.getPopulation() != null);
        boolean searchByCapital = (searchCondition.isCapital() != null);

        City[] result = new City[cities.length];
        int resultIndex = 0;

        for (City city : cities) {
            if (city != null) {
                boolean found = true;

                if (searchByName) {
                    found = searchCondition.getName().equals(city.getName());
                }

                if (found && searchByPopulation) {
                    found = searchCondition.getPopulation().equals(city.getPopulation());
                }

                if (found && searchByCapital) {
                    found = searchCondition.isCapital().equals(city.isCapital());
                }

                if (found) {
                    result[resultIndex] = city;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            City toReturn[] = new City[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    private List<City> getPageOfData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageOfData(cities, paginator.getLimit(), paginator.getOffset());
    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    private Optional<Integer> findCityIndexById(Long cityId) {
        OptionalInt optionalInt = IntStream.range(0, cities.length).filter(index ->
                Long.valueOf(cityId).equals(cities[index].getId())
        ).findAny();

        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}
