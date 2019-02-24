package ru.gorbach.hw5.storage;

import ru.gorbach.hw5.city.City;
import ru.gorbach.hw5.country.Country;
import ru.gorbach.hw5.customer.Customer;
import ru.gorbach.hw5.order.Order;

public class Storage {
    private static final int CAPACITY = 3;
    public static City[] cities = new City[CAPACITY];
    public static Country[] countries = new Country[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static Customer[] customers = new Customer[CAPACITY];
}
