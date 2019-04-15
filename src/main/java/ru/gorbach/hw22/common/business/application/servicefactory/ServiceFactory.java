package ru.gorbach.hw22.common.business.application.servicefactory;

import ru.gorbach.hw22.country.service.CountryService;
import ru.gorbach.hw22.city.service.CityService;
import ru.gorbach.hw22.customer.service.CustomerService;
import ru.gorbach.hw22.order.service.OrderService;

public interface ServiceFactory {

    CustomerService getCustomerService();

    OrderService getOrderService();

    CountryService getCountryService();

    CityService getCityService();
}
