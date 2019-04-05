package ru.gorbach.hw17.country.search;

public enum CountryOrderByField {
    NAME("countryName"), LANGUAGE("countryLanguage");

    CountryOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
