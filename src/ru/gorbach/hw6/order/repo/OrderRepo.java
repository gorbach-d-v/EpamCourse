package ru.gorbach.hw6.order.repo;

import ru.gorbach.hw6.common.business.repo.BaseRepo;
import ru.gorbach.hw6.order.domain.Order;
import ru.gorbach.hw6.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);
}
