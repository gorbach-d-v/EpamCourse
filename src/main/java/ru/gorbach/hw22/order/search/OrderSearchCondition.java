package ru.gorbach.hw22.order.search;

import ru.gorbach.hw22.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition {
    private Long customerId;
    private Long countryId;
    private Long cityId;
    private Integer price;
    private OrderOrderByField orderByField;

    public boolean searchByCustomerId() {
        return customerId != null;
    }

    public boolean searchByCountryId() {
        return customerId != null;
    }

    public boolean searchByCityId() {
        return cityId != null;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(OrderOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }

}
