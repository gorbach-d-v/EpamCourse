package ru.gorbach.hw16.common.business.application.servicefactory;

import ru.gorbach.hw16.city.repo.CityRepo;
import ru.gorbach.hw16.city.repo.impl.memory.CityCollectionRepo;
import ru.gorbach.hw16.city.service.CityService;
import ru.gorbach.hw16.city.service.impl.CityDefaultService;
import ru.gorbach.hw16.country.repo.CountryRepo;
import ru.gorbach.hw16.country.repo.impl.memory.CountryCollectionRepo;
import ru.gorbach.hw16.country.service.CountryService;
import ru.gorbach.hw16.country.service.impl.CountryDefaultService;
import ru.gorbach.hw16.customer.repo.CustomerRepo;
import ru.gorbach.hw16.customer.repo.impl.memory.CustomerCollectionRepo;
import ru.gorbach.hw16.customer.service.CustomerService;
import ru.gorbach.hw16.customer.service.impl.CustomerDefaultService;
import ru.gorbach.hw16.order.repo.OrderRepo;
import ru.gorbach.hw16.order.repo.impl.memory.OrderCollectionRepo;
import ru.gorbach.hw16.order.service.OrderService;
import ru.gorbach.hw16.order.service.impl.OrderDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    private CustomerRepo customerRepo = new CustomerCollectionRepo();
    private OrderRepo orderRepo = new OrderCollectionRepo();
    private CountryRepo countryRepo = new CountryCollectionRepo();
    private CityRepo cityRepo = new CityCollectionRepo();

    private OrderService orderService = new OrderDefaultService(orderRepo);
    private CustomerService customerService = new CustomerDefaultService(customerRepo, orderService);
    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityService, orderRepo);

    @Override
    public CustomerService getCustomerService() {
        return customerService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    @Override
    public CityService getCityService() {
        return cityService;
    }
}
