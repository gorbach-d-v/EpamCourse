package ru.gorbach.hw22;

import ru.gorbach.hw22.city.service.CityService;
import ru.gorbach.hw22.common.business.application.StorageType;
import ru.gorbach.hw22.common.business.application.servicefactory.ServiceSupplier;
import ru.gorbach.hw22.country.service.CountryService;
import ru.gorbach.hw22.customer.service.CustomerService;
import ru.gorbach.hw22.order.service.OrderService;
import ru.gorbach.hw22.storage.initor.fromsql.H2DBInitor;

public class Test {
    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.RELATIONAL_DB);
        }

        private CustomerService customerService = ServiceSupplier.getInstance().getCustomerService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();

        private void initialize() throws Exception {

            countryService.printAll();
        }
    }

    public static void main(String[] args) throws Exception {
        H2DBInitor h2DBInitor = new H2DBInitor();
        h2DBInitor.init();

        Application application = new Application();

        application.initialize();
    }
}
