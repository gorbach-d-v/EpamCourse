package ru.gorbach.hw6.order.repo.impl;

import ru.gorbach.hw6.order.domain.Order;
import ru.gorbach.hw6.order.repo.OrderRepo;
import ru.gorbach.hw6.order.search.OrderSearchCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw6.storage.Storage.orderList;

public class OrderMemoryListRepo implements OrderRepo {
    private int orderIndex = 0;

    @Override
    public void add(Order order) {
        orderList.add(order);
    }

    @Override
    public Order findById(long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            boolean searchByPrice = (searchCondition.getPrice()!= null);

            List<Order> result = new ArrayList<>();

            for (Order order : orderList) {
                if (order != null) {
                    boolean found = true;

                    if (searchByPrice) {
                        found = searchCondition.getPrice().equals(order.getPrice());
                    }

                    if (found && searchByPrice) {
                        orderList.add(order);
                    }
                }
            }
        return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        Order order = findOrderById(id);

        if (order != null){
            orderList.remove(order);
        }
    }

    @Override
    public void printAll(){
        for(Order order:orderList){
            System.out.println(order);
        }
    }

    private Order findOrderById(Long orderId) {
        for (Order order : orderList) {
            if (Long.valueOf(orderId).equals(order.getId())) {
                return order;
            }
        }
        return null;
    }
}