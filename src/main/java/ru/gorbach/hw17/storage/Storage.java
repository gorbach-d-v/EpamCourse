package ru.gorbach.hw17.storage;

import ru.gorbach.hw17.city.domain.City;
import ru.gorbach.hw17.country.domain.Country;
import ru.gorbach.hw17.customer.domain.Customer;
import ru.gorbach.hw17.order.domain.Order;

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
