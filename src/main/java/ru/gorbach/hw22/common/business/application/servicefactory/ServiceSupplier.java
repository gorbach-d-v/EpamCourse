package ru.gorbach.hw22.common.business.application.servicefactory;

import ru.gorbach.hw22.city.service.CityService;
import ru.gorbach.hw22.common.business.application.StorageType;
import ru.gorbach.hw22.country.service.CountryService;
import ru.gorbach.hw22.customer.service.CustomerService;
import ru.gorbach.hw22.order.service.OrderService;

public final class ServiceSupplier {
    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(StorageType storageType) {

        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    private ServiceSupplier(StorageType storageType) {
        initServiceFactory(storageType);
    }

    private void initServiceFactory(StorageType storageType) {
        switch (storageType) {
            case RELATIONAL_DB: {
                serviceFactory = new RelationalDBServiceFactory();
                break;
            }

            case MEMORY_ARRAY: {
                serviceFactory = new MemoryArrayServiceFactory();
                break;
            }

            default: {
                serviceFactory = new MemoryCollectionServiceFactory();
            }
        }
    }

    public CustomerService getCustomerService() {
        return serviceFactory.getCustomerService();
    }

    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }

    public CountryService getCountryService() {
        return serviceFactory.getCountryService();
    }

    public CityService getCityService() {
        return serviceFactory.getCityService();
    }
}
