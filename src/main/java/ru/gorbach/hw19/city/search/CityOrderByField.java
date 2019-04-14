package ru.gorbach.hw19.city.search;

public enum CityOrderByField {
    NAME("cityName"), POPULATION("cityPopulation");

    CityOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
