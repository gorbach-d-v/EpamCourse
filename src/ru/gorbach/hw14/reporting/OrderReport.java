package ru.gorbach.hw14.reporting;

import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.order.domain.Order;
import ru.gorbach.hw14.order.repo.impl.memory.OrderListRepo;
import ru.gorbach.hw14.order.search.OrderSearchCondition;
import ru.gorbach.hw14.order.service.OrderService;
import ru.gorbach.hw14.order.service.impl.OrderDefaultService;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class OrderReport {
    private static final String FILE_TO_WRITE_PATH = "./src/ru/gorbach/hw14/reporting/orders.txt";
    private static OrderService orderService = new OrderDefaultService(new OrderListRepo());

    public static void report() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_TO_WRITE_PATH))) {
            OrderSearchCondition orderSearchCondition = new OrderSearchCondition();
            //todo correct bad moment in pagination
            List<Order> orders = orderService.search(orderSearchCondition, new Pagination(0,100));

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
