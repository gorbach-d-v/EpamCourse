package ru.gorbach.hw4.order.repo;

import ru.gorbach.hw4.commons.ArrayUtils;
import ru.gorbach.hw4.order.Order;

import static ru.gorbach.hw4.storage.Storage.orders;

public class OrderMemoryRepo {
    private int orderIndex = 0;

    public void addOrder(Order order) {
        if (orderIndex == orders.length) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        orders[orderIndex] = order;
        orderIndex++;
    }

    public Order findOrderById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }

        return null;
    }

    public void deleteOrder(Order order) {
        Integer foundIndex = findOrderIndexByEntity(order);

        if (foundIndex != null) {
            deleteOrderByIndex(foundIndex);
        }
    }

    public void deleteOrder(Long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    public void printOrders(){
        for(Order order:orders){
            System.out.println(order);
        }
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    private Integer findOrderIndexById(Long orderId) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId().equals(orderId)) {
                return i;
            }
        }
        return null;
    }

    private Integer findOrderIndexByEntity(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) {
                return i;
            }
        }

        return null;
    }
}
