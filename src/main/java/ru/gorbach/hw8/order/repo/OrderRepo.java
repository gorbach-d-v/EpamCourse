package ru.gorbach.hw8.order.repo;

import ru.gorbach.hw8.common.business.repo.BaseRepo;
import ru.gorbach.hw8.order.domain.Order;
import ru.gorbach.hw8.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    void update(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);
}
