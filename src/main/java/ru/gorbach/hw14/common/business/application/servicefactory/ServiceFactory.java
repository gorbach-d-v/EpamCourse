package ru.gorbach.hw14.common.business.application.servicefactory;

import ru.gorbach.hw14.country.service.CountryService;
import ru.gorbach.hw14.customer.service.CustomerService;
import ru.gorbach.hw14.city.service.CityService;
import ru.gorbach.hw14.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
