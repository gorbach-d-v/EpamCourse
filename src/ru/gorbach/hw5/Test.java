package ru.gorbach.hw5;

import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.repo.impl.OrderMemoryRepo;

public class Test {
    public static void main(String[] args) {
        OrderMemoryRepo orderRepo = new OrderMemoryRepo();
        for (int i=0; i<4; i++) {
            orderRepo.add(new Order(i));
        }
        orderRepo.printOrders();
    }
}
