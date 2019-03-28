package ru.gorbach.hw9.order.search;

import ru.gorbach.hw9.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition {
    private Integer price;
    private OrderOrderByField orderByField;

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
