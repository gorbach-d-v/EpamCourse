package ru.gorbach.hw16.order.repo.impl.memory;

import ru.gorbach.hw16.order.domain.Order;
import ru.gorbach.hw16.order.search.OrderOrderByField;

import java.util.*;

import static ru.gorbach.hw16.order.search.OrderOrderByField.PRICE;

public class OrderComparatorComponent {
    private static final OrderComparatorComponent INSTANCE = new OrderComparatorComponent();
    private static Map<OrderOrderByField, Comparator<Order>> comparatorsByField = new HashMap<>();
    private static Set<OrderOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(PRICE));

    static {
        comparatorsByField.put(PRICE, getComparatorForPriceField());
    }

    private OrderComparatorComponent() {
    }

    public static OrderComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Order> getComparatorForPriceField() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return Integer.compare(order1.getPrice(), order2.getPrice());
            }
        };
    }

    public Comparator<Order> getComparatorForField(OrderOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Order> getComplexComparator(OrderOrderByField field) {
        return new Comparator<Order>() {

            @Override
            public int compare(Order m1, Order m2) {
                int result = 0;
                Comparator<Order> orderComparator = comparatorsByField.get(field);

                if (orderComparator != null) {
                    result = orderComparator.compare(m1, m2);
                    if (result == 0) {
                        for (OrderOrderByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(m1, m2);
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }

                return result;
            }
        };
    }
}
