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

        /*private void newOrders(int a){
            for (int i =0;i<a; i++){
                Customer customer = new Customer();
                customer.setFirstName("Name " + i);
                customer.setLastName("Surname " + i);
                City city = new City("City " + i, 100, false);
                Country country = new Country("Country " + i, "English");

                Order order = new Order(i*1000);
                order.setCustomer(customer);
                order.setCountry(country);
                order.setCity(city);
                orderService.add(order);
            }
        }*/
    }

    public static void main(String[] args) {
        //todo check correct delete function
        Application application = new Application();
        try {
            application.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //application.newOrders(4);
        //OrderReport.report();

    }
}
