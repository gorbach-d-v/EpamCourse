package ru.gorbach.hw5.order.service.impl;

import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.repo.OrderRepo;
import ru.gorbach.hw5.order.search.OrderSearchCondition;
import ru.gorbach.hw5.order.service.OrderService;

public class OrderDefaultService implements OrderService {
    private OrderRepo orderRepo;

    @Override
    public void add(Order order) {
        orderRepo.add(order);
    }

    @Override
    public Order findById(long id) {
        return orderRepo.findById(id);
    }

    @Override
    public Order[] search(OrderSearchCondition searchCondition) { return orderRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }
}
