package ru.gorbach.hw6.order.search;

import ru.gorbach.hw6.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition {
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
