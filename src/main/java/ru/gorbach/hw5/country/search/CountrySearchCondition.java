package ru.gorbach.hw5.country.search;

import ru.gorbach.hw5.common.business.search.BaseSearchCondition;

public class CountrySearchCondition extends BaseSearchCondition {
    private String name;
    private String language;

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
}
