package ru.gorbach.hw11.order.service.impl;

import ru.gorbach.hw11.order.domain.Order;
import ru.gorbach.hw11.order.repo.OrderRepo;
import ru.gorbach.hw11.order.search.OrderSearchCondition;
import ru.gorbach.hw11.order.service.OrderService;

import java.util.List;

public class OrderDefaultService implements OrderService {
    private OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Order order) {
        orderRepo.add(order);
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null) {
            orderRepo.update(order);
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return orderRepo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public void delete(Order order) {
        if (order.getId() != null) {
            orderRepo.deleteById(order.getId());
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }
}
