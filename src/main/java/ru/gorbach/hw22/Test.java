package ru.gorbach.hw22;

import ru.gorbach.hw22.city.service.CityService;
import ru.gorbach.hw22.common.application.ApplicationConfigurations;
import ru.gorbach.hw22.common.business.application.StorageType;
import ru.gorbach.hw22.common.business.application.servicefactory.ServiceSupplier;
import ru.gorbach.hw22.country.service.CountryService;
import ru.gorbach.hw22.customer.service.CustomerService;
import ru.gorbach.hw22.order.service.OrderService;
import ru.gorbach.hw22.storage.initor.StorageInitor;
import ru.gorbach.hw22.storage.initor.fromsql.H2DBInitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.RELATIONAL_DB);
        }

        private CustomerService customerService = ServiceSupplier.getInstance().getCustomerService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();

        private void initialize() throws Exception{
//            StorageInitor storageInitor = new StorageInitor(countryService);
//            String[] filePaths = {ApplicationConfigurations.INIT_DATA_XML_FILE1, ApplicationConfigurations.INIT_DATA_XML_FILE2};
//            List<String> filesWithInitData = new ArrayList<>(Arrays.asList(filePaths));
//            storageInitor.initStorageWithCountriesAndCities(filesWithInitData, StorageInitor.DataSourceType.STAX_XML_FILE);
            customerService.printAll();
        }
    }

    public static void main(String[] args) throws Exception {
        H2DBInitor h2DBInitor = new H2DBInitor();
        h2DBInitor.init();

        Application application = new Application();

        application.initialize();
    }
}
