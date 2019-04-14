package ru.gorbach.hw20.order.repo.impl.memory;

import ru.gorbach.hw20.order.domain.Order;
import ru.gorbach.hw20.order.search.OrderOrderByField;
import ru.gorbach.hw20.order.search.OrderSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderOrderingComponent {
    public void applyOrdering(List<Order> orders, OrderSearchCondition orderSearchCondition) {
        Comparator<Order> orderComparator = null;

        OrderOrderByField field = orderSearchCondition.getOrderByField();
        switch (orderSearchCondition.getOrderType()) {

            case SIMPLE: {
                orderComparator = OrderComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                orderComparator = OrderComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (orderComparator != null) {
            switch (orderSearchCondition.getOrderDirection()) {

                case ASC:
                    Collections.sort(orders, orderComparator);
                    break;
                case DESC:
                    Collections.sort(orders, orderComparator.reversed());
                    break;
            }
        }
    }
}
