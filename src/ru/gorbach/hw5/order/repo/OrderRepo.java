package ru.gorbach.hw5.order.repo;

import ru.gorbach.hw5.common.business.repo.BaseRepo;
import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.search.OrderSearchCondition;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    Order findById(long id);

    Order[] search(OrderSearchCondition searchCondition);
}
