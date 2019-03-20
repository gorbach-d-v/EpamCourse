package ru.gorbach.hw11.common.business.application.servicefactory;

import ru.gorbach.hw11.city.repo.impl.memory.CityListRepo;
import ru.gorbach.hw11.city.service.CityService;
import ru.gorbach.hw11.city.service.impl.CityDefaultService;
import ru.gorbach.hw11.country.repo.impl.memory.CountryListRepo;
import ru.gorbach.hw11.country.service.CountryService;
import ru.gorbach.hw11.country.service.impl.CountryDefaultService;
import ru.gorbach.hw11.customer.repo.impl.memory.CustomerListRepo;
import ru.gorbach.hw11.customer.service.CustomerService;
import ru.gorbach.hw11.customer.service.impl.CustomerDefaultService;
import ru.gorbach.hw11.order.repo.impl.memory.OrderListRepo;
import ru.gorbach.hw11.order.service.OrderService;
import ru.gorbach.hw11.order.service.impl.OrderDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    @Override
    public CustomerService getCustomerService() {
        return new CustomerDefaultService(new CustomerListRepo(), new OrderListRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderListRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryListRepo(), new CityListRepo(), new OrderListRepo());
    }

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityListRepo(), new OrderListRepo());
    }
}
