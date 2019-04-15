package ru.gorbach.hw22.city.search;

import ru.gorbach.hw22.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition {
    private Long countryId;
    private String name;
    private Integer population;
    private Boolean isCapital;
    private CityOrderByField orderByField;

    public boolean searchByCountryId() {
        return countryId != null;
    }

    public boolean searchByName() {
        return name == null || name.isEmpty();
    }

    public boolean searchByPopulation() {
        return population == null;
    }

    public boolean searchByCapital() {
        return isCapital == null;
    }


    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

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

