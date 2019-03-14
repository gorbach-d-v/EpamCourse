package ru.gorbach.hw9;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.repo.impl.memory.CityListRepo;
import ru.gorbach.hw9.common.business.search.OrderDirection;
import ru.gorbach.hw9.city.search.CitySearchCondition;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        City city1 = new City("Brest", 540, false);
        City city2 = new City("Saratov", 480, false);
        City city3 = new City("Stavropol", 900, false);
        City city4 = new City("Moscow", 1400, true);
        City city5 = new City("Kaliningrd", 150, false);
        City city6 = new City("Rostov", 740, false);
        City city7 = new City("Kiev", 1070, true);


        CityListRepo cityListRepo = new CityListRepo();
        cityListRepo.add(city1);
        cityListRepo.add(city2);
        cityListRepo.add(city3);
        cityListRepo.add(city4);
        cityListRepo.add(city5);
        cityListRepo.add(city6);
        cityListRepo.add(city7);

        cityListRepo.printAll();

        CitySearchCondition citySearchCondition = new CitySearchCondition();
        citySearchCondition.setOrderDirection(OrderDirection.DESC);


        List<City> searchList = cityListRepo.search(citySearchCondition);

        System.out.println("---------Custom sort---------");
        for (City city : searchList) {
            System.out.println(city);
        }

    }
}
