package ru.gorbach.hw9.order.repo;

import ru.gorbach.hw9.common.business.repo.BaseRepo;
import ru.gorbach.hw9.order.domain.Order;
import ru.gorbach.hw9.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    void update(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);
}
