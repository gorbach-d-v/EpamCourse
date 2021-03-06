package ru.gorbach.hw22.country.search;

import ru.gorbach.hw22.common.business.search.BaseSearchCondition;

public class CountrySearchCondition extends BaseSearchCondition {
    private String name;
    private String language;
    private CountryOrderByField orderByField;

    public boolean searchByName() {
        return name == null || name.isEmpty();
    }

    public boolean searchByLanguage() {
        return language == null || language.isEmpty();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public CountryOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CountryOrderByField countryOrderByField) {
        this.orderByField = countryOrderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }

}
