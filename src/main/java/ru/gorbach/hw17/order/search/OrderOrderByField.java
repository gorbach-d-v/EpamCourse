package ru.gorbach.hw17.order.search;

public enum OrderOrderByField {
    PRICE("orderPrice");

    OrderOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
