package ru.gorbach.hw20.common.business.application.servicefactory;

import ru.gorbach.hw20.city.service.CityService;
import ru.gorbach.hw20.country.service.CountryService;
import ru.gorbach.hw20.customer.service.CustomerService;
import ru.gorbach.hw20.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
