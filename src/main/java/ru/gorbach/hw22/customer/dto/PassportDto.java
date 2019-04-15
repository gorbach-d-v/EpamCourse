package ru.gorbach.hw22.customer.dto;

public class PassportDto {
    private String serial;
    private String number;

    public PassportDto(String serial, String number) {
        this.serial = serial;
        this.number = number;
    }

    public String getSerial() {
        return serial;
    }

    public String getNumber() {
        return number;
    }
}
