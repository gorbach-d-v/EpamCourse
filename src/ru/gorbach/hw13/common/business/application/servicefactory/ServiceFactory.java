package ru.gorbach.hw13.common.business.application.servicefactory;

import ru.gorbach.hw13.country.service.CountryService;
import ru.gorbach.hw13.customer.service.CustomerService;
import ru.gorbach.hw13.city.service.CityService;
import ru.gorbach.hw13.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
