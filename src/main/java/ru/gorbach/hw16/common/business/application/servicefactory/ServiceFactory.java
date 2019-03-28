package ru.gorbach.hw16.common.business.application.servicefactory;

import ru.gorbach.hw16.country.service.CountryService;
import ru.gorbach.hw16.city.service.CityService;
import ru.gorbach.hw16.customer.service.CustomerService;
import ru.gorbach.hw16.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
