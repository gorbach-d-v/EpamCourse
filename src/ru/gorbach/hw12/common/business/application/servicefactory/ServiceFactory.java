package ru.gorbach.hw12.common.business.application.servicefactory;

import ru.gorbach.hw12.country.service.CountryService;
import ru.gorbach.hw12.city.service.CityService;
import ru.gorbach.hw12.customer.service.CustomerService;
import ru.gorbach.hw12.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
