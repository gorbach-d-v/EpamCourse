package ru.gorbach.hw22.order.repo.impl.jdbc;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.common.business.exception.jdbc.ResultSetMappingException;
import ru.gorbach.hw22.country.domain.ProxyCountry;
import ru.gorbach.hw22.customer.domain.Customer;
import ru.gorbach.hw22.order.domain.Order;

import java.sql.ResultSet;

public final class OrderMapper {
    private static final String ORDER_CLASS_NAME = Order.class.getSimpleName();

    private OrderMapper() {
    }

    public static Order mapOrder(ResultSet rs) throws ResultSetMappingException {
        try {
            Order order = new Order();
            order.setId(rs.getLong("ID"));
            order.setCustomer(new Customer(rs.getLong("USER_ID")));
            order.setCountry(new ProxyCountry(rs.getLong("COUNTRY_ID")));
            order.setCity(new City(rs.getLong("CITY_ID")));
            order.setPrice(rs.getInt("PRICE"));
            return order;
        } catch (Exception e) {
            throw new ResultSetMappingException(ORDER_CLASS_NAME, e);
        }
    }
}
