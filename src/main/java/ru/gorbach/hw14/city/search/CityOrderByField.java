package ru.gorbach.hw14.city.search;

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
