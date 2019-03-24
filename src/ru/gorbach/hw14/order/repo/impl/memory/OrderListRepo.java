package ru.gorbach.hw14.order.repo.impl.memory;

import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.common.solutions.paginationutils.PaginationUtils;
import ru.gorbach.hw14.order.domain.Order;
import ru.gorbach.hw14.order.repo.OrderRepo;
import ru.gorbach.hw14.order.search.OrderSearchCondition;
import ru.gorbach.hw14.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw14.storage.Storage.orderList;

public class OrderListRepo implements OrderRepo {
    private OrderOrderingComponent orderingComponent = new OrderOrderingComponent();

    @Override
    public void add(Order order) {
        order.setId(SequenceGenerator.generateId());
        orderList.add(order);
    }

    @Override
    public void update(Order order) {
        // nothing to update
    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition, Pagination pagination) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Order> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            return PaginationUtils.getLimitList(result,pagination);
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

                if (found) {
                    result.add(order);
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

    @Override
    public int countByCity(Long cityId) {
        int count = 0;
        for (Order order : orderList) {
            if (cityId == order.getCity().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByCountry(Long countryId) {
        int count = 0;
        for (Order order : orderList) {
            if (countryId == order.getCountry().getId()) {
                count++;
            }
        }
        return count;
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