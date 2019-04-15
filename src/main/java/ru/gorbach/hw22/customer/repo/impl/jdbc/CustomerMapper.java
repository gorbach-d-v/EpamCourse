package ru.gorbach.hw22.customer.repo.impl.jdbc;

import ru.gorbach.hw22.common.business.exception.jdbc.ResultSetMappingException;
import ru.gorbach.hw22.customer.domain.Customer;

import java.sql.ResultSet;

public final class CustomerMapper {
    private static final String CUSTOMER_CLASS_NAME = Customer.class.getSimpleName();

    private CustomerMapper() {
    }

    public static Customer mapCustomer(ResultSet rs) throws ResultSetMappingException {
        try {
            Customer customer = new Customer();
            customer.setId(rs.getLong("ID"));
            customer.setFirstName(rs.getString("FIRST_NAME"));
            customer.setLastName(rs.getString("LAST_NAME"));

            return customer;
        } catch (Exception e) {
            throw new ResultSetMappingException(CUSTOMER_CLASS_NAME, e);
        }
    }
}
