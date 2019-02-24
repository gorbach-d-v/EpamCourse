package ru.gorbach.hw2;

public class Passport {
    private String serial;
    private String number;

    public Passport(String serial, String number) {
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
