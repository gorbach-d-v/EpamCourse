package ru.gorbach.hw20.order.repo;

import ru.gorbach.hw20.common.solutions.repo.BaseRepo;
import ru.gorbach.hw20.order.domain.Order;
import ru.gorbach.hw20.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {
    List<Order> search(OrderSearchCondition searchCondition);

    int countByCity(Long cityId);

    int countByCountry(Long countryId);
}
