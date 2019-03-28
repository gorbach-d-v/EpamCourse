package ru.gorbach.hw8.order.service;

import ru.gorbach.hw8.common.business.service.BaseService;
import ru.gorbach.hw8.order.domain.Order;
import ru.gorbach.hw8.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService {

    void add(Order order);

    void update(Order order);

    Order findById(Long id);

    void delete(Order order);

    List<Order> search(OrderSearchCondition searchCondition);
}
