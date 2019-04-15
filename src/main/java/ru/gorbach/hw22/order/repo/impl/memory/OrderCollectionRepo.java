package ru.gorbach.hw22.order.repo.impl.memory;

import ru.gorbach.hw22.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw22.common.business.search.Paginator;
import ru.gorbach.hw22.order.domain.Order;
import ru.gorbach.hw22.order.repo.OrderRepo;
import ru.gorbach.hw22.order.search.OrderSearchCondition;
import ru.gorbach.hw22.storage.SequenceGenerator;

import java.util.*;

import static ru.gorbach.hw22.storage.Storage.orderList;

public class OrderCollectionRepo implements OrderRepo {
    private OrderOrderingComponent orderingComponent = new OrderOrderingComponent();

    @Override
    public Order add(Order order) {
        order.setId(SequenceGenerator.generateId());
        orderList.add(order);
        return order;
    }

    @Override
    public void add(Collection<Order> ordersList) {
        for (Order order : ordersList) {
            add(order);
        }
    }

    @Override
    public void update(Order order) {
        // nothing to update
    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            Optional<Order> optionalCountry = findById(searchCondition.getId());
            if (optionalCountry.isPresent()) {
                return Collections.singletonList(optionalCountry.get());
            } else {
                return Collections.emptyList();
            }
        } else {
            List<Order> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            if (!result.isEmpty() && searchCondition.needPagination()) {
                result = getPageOfData(result, searchCondition.getPaginator());
            }
            return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Order> foundOptional = findOrderById(id);
        foundOptional.map(country -> orderList.remove(country));
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

    @Override
    public List<Order> findAll() {
        return orderList;
    }

    @Override
    public int countAll() {
        return orderList.size();
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

    private List<Order> getPageOfData(List<Order> orders, Paginator paginator) {
        return CollectionUtils.getPageOfData(orders, paginator.getLimit(), paginator.getOffset());
    }

    private Optional<Order> findOrderById(Long orderId) {
        return orderList.stream().filter(country -> Long.valueOf(orderId).equals(country.getId())).findAny();
    }
}