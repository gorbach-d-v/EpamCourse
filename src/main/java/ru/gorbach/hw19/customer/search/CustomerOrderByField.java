package ru.gorbach.hw19.customer.search;

public enum CustomerOrderByField {
    FIRSTNAME("firstName"), LASTNAME("lastName");

    CustomerOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
