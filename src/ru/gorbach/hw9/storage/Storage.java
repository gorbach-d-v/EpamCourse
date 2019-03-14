package ru.gorbach.hw9.storage;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.customer.domain.Customer;
import ru.gorbach.hw9.country.domain.Country;
import ru.gorbach.hw9.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final int CAPACITY = 3;
    public static City[] cities = new City[CAPACITY];
    public static Country[] countries = new Country[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static Customer[] customers = new Customer[CAPACITY];

    public static List<City> cityList = new ArrayList<>();
    public static List<Country> countryList = new ArrayList<>();
    public static List<Order> orderList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();
}
