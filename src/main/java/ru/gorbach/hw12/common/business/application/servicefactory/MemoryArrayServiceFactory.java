package ru.gorbach.hw12.common.business.application.servicefactory;

import ru.gorbach.hw12.city.repo.impl.memory.CityArrayRepo;
import ru.gorbach.hw12.city.service.impl.CityDefaultService;
import ru.gorbach.hw12.country.service.CountryService;
import ru.gorbach.hw12.customer.service.impl.CustomerDefaultService;
import ru.gorbach.hw12.city.service.CityService;
import ru.gorbach.hw12.country.repo.impl.memory.CountryArrayRepo;
import ru.gorbach.hw12.country.service.impl.CountryDefaultService;
import ru.gorbach.hw12.customer.repo.impl.memory.CustomerArrayRepo;
import ru.gorbach.hw12.customer.service.CustomerService;
import ru.gorbach.hw12.order.repo.impl.memory.OrderArrayRepo;
import ru.gorbach.hw12.order.service.OrderService;
import ru.gorbach.hw12.order.service.impl.OrderDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {

    @Override
    public CustomerService getCustomerService() {
        return new CustomerDefaultService(new CustomerArrayRepo(), new OrderArrayRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderArrayRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryArrayRepo(), new CityArrayRepo(), new OrderArrayRepo());
    }

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityArrayRepo(), new OrderArrayRepo());
    }
}
