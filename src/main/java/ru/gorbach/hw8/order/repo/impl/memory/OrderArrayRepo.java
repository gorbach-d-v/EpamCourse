package ru.gorbach.hw8.order.repo.impl.memory;

import ru.gorbach.hw8.common.solutions.ArrayUtils;
import ru.gorbach.hw8.order.domain.Order;
import ru.gorbach.hw8.order.repo.OrderRepo;
import ru.gorbach.hw8.order.search.OrderSearchCondition;
import ru.gorbach.hw8.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw8.storage.Storage.orders;

public class OrderArrayRepo implements OrderRepo {
    private OrderOrderingComponent orderingComponent = new OrderOrderingComponent();
    private int orderIndex = 0;

    @Override
    public void add(Order order) {
        if (orderIndex == orders.length) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        order.setId(sequenceGenerator.generateId());
        orders[orderIndex] = order;
        orderIndex++;
    }

    @Override
    public void update(Order order) {
        // nothing to update
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
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            List<Order> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            return result;
        }
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        boolean searchByPrice = (searchCondition.getPrice() != null);


        Order[] result = new Order[orders.length];
        int resultIndex = 0;

        for (Order order : orders) {
            if (order != null) {
                boolean found = true;

                if (searchByPrice) {
                    found = searchCondition.getPrice().equals(order.getPrice());
                }

                if (found && searchByPrice) {
                    result[resultIndex] = order;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            Order toReturn[] = new Order[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    @Override
    public void printAll() {
        for (Order order : orders) {
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
