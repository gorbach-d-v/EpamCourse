package ru.gorbach.hw11.city.search;

import ru.gorbach.hw11.city.domain.Climate;
import ru.gorbach.hw11.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition {
    private String name;
    private Integer population;
    private Boolean isCapital;
    private Climate climate;
    private CityOrderByField orderByField;

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

    public CityOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CityOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }

}

