package ru.gorbach.hw20.reporting;

import ru.gorbach.hw20.order.domain.Order;
import ru.gorbach.hw20.order.repo.impl.memory.OrderCollectionRepo;
import ru.gorbach.hw20.order.search.OrderSearchCondition;
import ru.gorbach.hw20.order.service.OrderService;
import ru.gorbach.hw20.order.service.impl.OrderDefaultService;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class OrderReport {
    private static final String FILE_TO_WRITE_PATH = "./src/ru/gorbach/hw20/reporting/orders.txt";
    private static OrderService orderService = new OrderDefaultService(new OrderCollectionRepo());

    public static void report() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_TO_WRITE_PATH))) {
            OrderSearchCondition orderSearchCondition = new OrderSearchCondition();
            List<Order> orders = orderService.search(orderSearchCondition);

            if (orders.isEmpty()) {
                writer.println("Order list is empty");
            }

            for (Order order : orders) {
                writer.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
