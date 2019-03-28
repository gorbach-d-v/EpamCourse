package ru.gorbach.hw5.storage;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.customer.domain.BaseCustomer;
import ru.gorbach.hw5.order.domain.Order;

public class Storage {
    private static final int CAPACITY = 3;
    public static City[] cities = new City[CAPACITY];
    public static Country[] countries = new Country[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static BaseCustomer[] customers = new BaseCustomer[CAPACITY];
}
