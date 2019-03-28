package ru.gorbach.hw4.storage;

import ru.gorbach.hw4.city.City;
import ru.gorbach.hw4.country.Country;
import ru.gorbach.hw4.customer.Customer;
import ru.gorbach.hw4.order.Order;

public class Storage {
    private static final int CAPACITY = 3;
    public static City[] cities = new City[CAPACITY];
    public static Country[] countries = new Country[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static Customer[] customers = new Customer[CAPACITY];
}
