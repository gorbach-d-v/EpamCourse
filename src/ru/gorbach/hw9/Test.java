package ru.gorbach.hw9;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.repo.impl.CityMemoryListRepo;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.common.business.search.SortType;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        City city1 = new City("Brest",540,false);
        City city2 = new City("Saratov",480,false);
        City city3 = new City("Stavropol",900,false);
        City city4 = new City("Moscow",1400,true);
        City city5 = new City("Kaliningrd",150,false);
        City city6 = new City("Rostov",740,false);
        City city7 = new City("Kiev",1070,true);


        CityMemoryListRepo cityMemoryListRepo = new CityMemoryListRepo();
        cityMemoryListRepo.add(city1);
        cityMemoryListRepo.add(city2);
        cityMemoryListRepo.add(city3);
        cityMemoryListRepo.add(city4);
        cityMemoryListRepo.add(city5);
        cityMemoryListRepo.add(city6);
        cityMemoryListRepo.add(city7);

        cityMemoryListRepo.printAll();

        CitySearchCondition citySearchCondition = new CitySearchCondition();
        citySearchCondition.setSortType(SortType.DESC);
        citySearchCondition.sortByName();
//        citySearchCondition.setSortTypeById(SortType.DESC);
//        citySearchCondition.setSortTypeById(SortType.ASC);
//        citySearchCondition.setSortTypeByName(SortType.DESC);
//        citySearchCondition.setSortTypeByName(SortType.ASC);
//        citySearchCondition.setSortTypeByPopulation(SortType.DESC);
//        citySearchCondition.setSortTypeByPopulation(SortType.ASC);

        List<City> searchList = cityMemoryListRepo.search(citySearchCondition);

        System.out.println("---------Custom sort---------");
        for (City city : searchList){
            System.out.println(city);
        }

    }
}
