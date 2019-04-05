package ru.gorbach.hw17.common.business.application.servicefactory;

import ru.gorbach.hw17.city.service.CityService;
import ru.gorbach.hw17.country.service.CountryService;
import ru.gorbach.hw17.customer.service.CustomerService;
import ru.gorbach.hw17.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
