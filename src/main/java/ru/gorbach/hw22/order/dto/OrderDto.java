package ru.gorbach.hw22.order.dto;

import ru.gorbach.hw22.city.dto.CityDto;
import ru.gorbach.hw22.country.dto.CountryDto;
import ru.gorbach.hw22.customer.dto.CustomerDto;
import ru.gorbach.hw22.common.business.dto.BaseDto;

public class OrderDto extends BaseDto {
    private int price;
    private CustomerDto customer;
    private CountryDto country;
    private CityDto city;

    public OrderDto() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Order " + id + ": ");
        str.append("Customer - ");
        str.append((customer != null) ? customer.getFirstName() + " " + customer.getLastName() : "no info");
        str.append("; Country - ");
        str.append((country != null) ? country.getName() : "no info");
        str.append("; City - ");
        str.append((city != null) ? city.getName() : "no info");
        return str.toString();
    }
}
