package ru.gorbach.hw9.order.repo.impl.memory;

import ru.gorbach.hw9.order.domain.Order;
import ru.gorbach.hw9.order.repo.OrderRepo;
import ru.gorbach.hw9.order.search.OrderSearchCondition;
import ru.gorbach.hw9.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw9.storage.Storage.orderList;

public class OrderListRepo implements OrderRepo {
    private OrderOrderingComponent orderingComponent = new OrderOrderingComponent();

    @Override
    public void add(Order order) {
        order.setId(sequenceGenerator.generateId());
        orderList.add(order);
    }

    @Override
    public void update(Order order) {
        // nothing to update
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

    @Override
    public void deleteById(Long id) {
        Order order = findOrderById(id);

        if (order != null) {
            orderList.remove(order);
        }
    }

    @Override
    public void printAll() {
        for (Order order : orderList) {
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