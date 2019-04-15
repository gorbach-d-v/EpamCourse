package ru.gorbach.hw22.customer.dto;

import ru.gorbach.hw22.common.business.dto.BaseDto;

public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
    private PassportDto passport;

    public CustomerDto() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassport(PassportDto passport) {
        this.passport = passport;
    }

    public PassportDto getPassport() {
        return passport;
    }
}
