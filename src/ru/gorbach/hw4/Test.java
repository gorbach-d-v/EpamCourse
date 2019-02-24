package ru.gorbach.hw4;

import ru.gorbach.hw4.order.Order;
import ru.gorbach.hw4.order.repo.OrderMemoryRepo;

public class Test {
    public static void main(String[] args) {
        OrderMemoryRepo orderRepo = new OrderMemoryRepo();
        for (int i=0; i<4; i++) {
            orderRepo.addOrder(new Order(i));
        }
        orderRepo.printOrders();
    }
}
