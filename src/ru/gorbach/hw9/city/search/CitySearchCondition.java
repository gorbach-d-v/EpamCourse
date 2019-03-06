package ru.gorbach.hw9.city.search;

import ru.gorbach.hw9.city.domain.Climate;
import ru.gorbach.hw9.common.business.search.BaseSearchCondition;
import ru.gorbach.hw9.common.business.search.SortType;

public class CitySearchCondition extends BaseSearchCondition {
    private String name;
    private Integer population;
    private Boolean isCapital;
    private Climate climate;

    private boolean isSortByName = false;
    private boolean isSortByPopulation = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Boolean isCapital() {
        return isCapital;
    }

    public void setCapital(Boolean capital) {
        isCapital = capital;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public boolean isSortByName() {
        return isSortByName;
    }

    public boolean isSortByPopulation() {
        return isSortByPopulation;
    }

    public void sortById() {
        isSortById = true;
        isSortByName = false;
        isSortByPopulation = false;
    }

    public void sortByName() {
        isSortById = false;
        isSortByName = true;
        isSortByPopulation = false;
    }

    public void sortByPopulation() {
        isSortById = false;
        isSortByName = false;
        isSortByPopulation = true;
    }
}

