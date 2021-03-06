package ru.gorbach.hw4.order.service;

import ru.gorbach.hw4.order.Order;
import ru.gorbach.hw4.order.repo.OrderMemoryRepo;

public class OrderMemoryService {
    private OrderMemoryRepo orderRepo = new OrderMemoryRepo();

    public void addOrder(Order order) {
        orderRepo.addOrder(order);
    }

    public Order findOrderById(long id) {
        return orderRepo.findOrderById(id);
    }

    public void deleteOrder(Order order) {
        orderRepo.deleteOrder(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteOrder(id);
    }

    public void printOrders() {
        orderRepo.printOrders();
    }
}
