package ru.gorbach.hw11;

import ru.gorbach.hw11.city.service.CityService;
import ru.gorbach.hw11.common.business.application.StorageType;
import ru.gorbach.hw11.common.business.application.servicefactory.ServiceSupplier;
import ru.gorbach.hw11.country.service.CountryService;
import ru.gorbach.hw11.customer.service.CustomerService;
import ru.gorbach.hw11.order.service.OrderService;
import ru.gorbach.hw11.storage.initor.StorageInitor;
import ru.gorbach.hw11.storage.initor.StorageInitorConstants;

public class Test {
    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
        }

        private CustomerService customerService = ServiceSupplier.getInstance().getCustomerService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();

        private void initialize() throws Exception {
            StorageInitor storageInitor = new StorageInitor(countryService);
            storageInitor.initStorageWithCountriesAndCities(StorageInitorConstants.INIT_DATA_TXT_FILE);
            countryService.printAll();
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
