package ru.gorbach.hw12.storage;

import ru.gorbach.hw12.city.domain.City;
import ru.gorbach.hw12.country.domain.Country;
import ru.gorbach.hw12.customer.domain.Customer;
import ru.gorbach.hw12.order.domain.Order;

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
