package ru.gorbach.hw19.common.business.application.servicefactory;

import ru.gorbach.hw19.country.service.CountryService;
import ru.gorbach.hw19.city.service.CityService;
import ru.gorbach.hw19.customer.service.CustomerService;
import ru.gorbach.hw19.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
