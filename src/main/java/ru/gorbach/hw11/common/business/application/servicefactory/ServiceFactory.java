package ru.gorbach.hw11.common.business.application.servicefactory;

import ru.gorbach.hw11.city.service.CityService;
import ru.gorbach.hw11.country.service.CountryService;
import ru.gorbach.hw11.customer.service.CustomerService;
import ru.gorbach.hw11.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
