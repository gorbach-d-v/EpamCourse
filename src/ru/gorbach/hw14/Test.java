package ru.gorbach.hw14;

import ru.gorbach.hw14.city.service.CityService;
import ru.gorbach.hw14.common.application.ApplicationConfigurations;
import ru.gorbach.hw14.common.business.application.StorageType;
import ru.gorbach.hw14.common.business.application.servicefactory.ServiceSupplier;
import ru.gorbach.hw14.country.service.CountryService;
import ru.gorbach.hw14.customer.service.CustomerService;
import ru.gorbach.hw14.order.service.OrderService;
import ru.gorbach.hw14.storage.initor.StorageInitor;

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
            storageInitor.initStorageWithCountriesAndCities(ApplicationConfigurations.INIT_DATA_XML_FILE, StorageInitor.DataSourceType.STAX_XML_FILE);
            countryService.printAll();
        }

    }

    public static void main(String[] args) {
        Application application = new Application();
        /*try {
            application.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
