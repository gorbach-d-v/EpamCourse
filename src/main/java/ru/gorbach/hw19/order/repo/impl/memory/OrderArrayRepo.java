package ru.gorbach.hw19.order.repo.impl.memory;

import ru.gorbach.hw19.common.business.search.Paginator;
import ru.gorbach.hw19.common.solutions.utils.ArrayUtils;
import ru.gorbach.hw19.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw19.order.domain.Order;
import ru.gorbach.hw19.order.repo.OrderRepo;
import ru.gorbach.hw19.order.search.OrderSearchCondition;
import ru.gorbach.hw19.storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static ru.gorbach.hw19.storage.Storage.orders;

public class OrderArrayRepo implements OrderRepo {
    private OrderOrderingComponent orderingComponent = new OrderOrderingComponent();
    private int orderIndex = 0;

    @Override
    public Order add(Order order) {
        if (orderIndex == orders.length) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        order.setId(SequenceGenerator.generateId());
        orders[orderIndex] = order;
        orderIndex++;
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
        return findOrderIndexById(id).map(index -> orders[index]);
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
        findOrderIndexById(id).ifPresent(this::deleteOrderByIndex);
    }

    @Override
    public void printAll() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Override
    public int countByCity(Long cityId) {
        int count = 0;
        for (Order order : orders) {
            if (cityId == order.getCity().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByCountry(Long countryId) {
        int count = 0;
        for (Order order : orders) {
            if (countryId == order.getCountry().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(Arrays.asList(orders));
    }

    @Override
    public int countAll() {
        return orders.length;
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

                if (found) {
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

    private List<Order> getPageOfData(List<Order> orders, Paginator paginator) {
        return CollectionUtils.getPageOfData(orders, paginator.getLimit(), paginator.getOffset());
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    private Optional<Integer> findOrderIndexById(Long orderId) {
        OptionalInt optionalInt = IntStream.range(0, orders.length).filter(index ->
                Long.valueOf(orderId).equals(orders[index].getId())
        ).findAny();

        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}
