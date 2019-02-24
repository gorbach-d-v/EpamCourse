package ru.gorbach.hw5.order.repo;

import ru.gorbach.hw5.order.Order;

public interface OrderRepoI {
    void addOrder(Order order);

    Order findOrderById(long id);

    void deleteOrder(Order order);

    void deleteOrder(Long id);

    void printOrders();
}
