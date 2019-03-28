package ru.gorbach.hw5.order.service;

import ru.gorbach.hw5.common.business.service.BaseService;
import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.search.OrderSearchCondition;

public interface OrderService extends BaseService {

    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
