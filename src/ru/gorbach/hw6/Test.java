package ru.gorbach.hw6;

import ru.gorbach.hw6.order.domain.Order;
import ru.gorbach.hw6.order.repo.impl.OrderMemoryArrayRepo;

public class Test {
    public static void main(String[] args) {
        OrderMemoryArrayRepo orderRepo = new OrderMemoryArrayRepo();
        for (int i=0; i<4; i++) {
            orderRepo.add(new Order(i));
        }
        orderRepo.printOrders();
    }
}
