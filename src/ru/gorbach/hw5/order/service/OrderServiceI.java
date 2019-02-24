package ru.gorbach.hw5.order.service;

import ru.gorbach.hw5.order.Order;

public interface OrderServiceI {
    void addOrder(Order order);

    Order findOrderById(long id);

    void deleteOrder(Order order);

    void deleteOrder(Long id);

    void printOrders();
}
