package ru.gorbach.hw22.order.service;

import ru.gorbach.hw22.common.solutions.service.BaseService;
import ru.gorbach.hw22.order.domain.Order;
import ru.gorbach.hw22.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

}
