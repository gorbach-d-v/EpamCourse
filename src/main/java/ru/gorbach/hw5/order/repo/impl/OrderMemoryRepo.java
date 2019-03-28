package ru.gorbach.hw5.order.repo.impl;

import ru.gorbach.hw5.common.Solutions.ArrayUtils;
import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.repo.OrderRepo;
import ru.gorbach.hw5.order.search.OrderSearchCondition;

import static ru.gorbach.hw5.storage.Storage.orders;

public class OrderMemoryRepo implements OrderRepo {
    private int orderIndex = 0;

    @Override
    public void add(Order order) {
        if (orderIndex == orders.length) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        orders[orderIndex] = order;
        orderIndex++;
    }

    @Override
    public Order findById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }

        return null;
    }

    @Override
    public Order[] search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return new Order[]{findById(searchCondition.getId())};
        } else {

            boolean searchByPrice = (searchCondition.getPrice()!=null);

            
            Order[] result = new Order[orders.length];
            int resultIndex = 0;

            for (Order city : orders) {
                if (city != null) {
                    boolean found = true;

                    if (searchByPrice) {
                        found = searchCondition.getPrice().equals(city.getPrice());
                    }

                    if (found && searchByPrice) {
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Order toReturn[] = new Order[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return new Order[0];
    }
    
    @Override
    public void deleteById(Long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    @Override
    public void printAll(){
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
}
