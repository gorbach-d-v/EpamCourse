package ru.gorbach.hw9.order.repo;

import ru.gorbach.hw9.common.solutions.repo.BaseRepo;
import ru.gorbach.hw9.order.domain.Order;
import ru.gorbach.hw9.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order,Long> {
    List<Order> search(OrderSearchCondition searchCondition);
}
