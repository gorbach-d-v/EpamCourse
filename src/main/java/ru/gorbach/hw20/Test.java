package ru.gorbach.hw20;

import ru.gorbach.hw20.city.service.CityService;
import ru.gorbach.hw20.common.application.ApplicationConfigurations;
import ru.gorbach.hw20.common.business.application.StorageType;
import ru.gorbach.hw20.common.business.application.servicefactory.ServiceSupplier;
import ru.gorbach.hw20.country.service.CountryService;
import ru.gorbach.hw20.customer.service.CustomerService;
import ru.gorbach.hw20.order.service.OrderService;
import ru.gorbach.hw20.storage.initor.StorageInitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            String[] filePaths = {ApplicationConfigurations.INIT_DATA_XML_FILE1, ApplicationConfigurations.INIT_DATA_XML_FILE2};
            List<String> filesWithInitData = new ArrayList<>(Arrays.asList(filePaths));
            storageInitor.initStorageWithCountriesAndCities(filesWithInitData, StorageInitor.DataSourceType.STAX_XML_FILE);
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
