package ru.gorbach.hw22.country.dto;

import ru.gorbach.hw22.country.domain.CountryDiscriminator;

public class CountryWithColdClimateDto extends CountryDto {
    private int telephoneCode;
    private Boolean polarNight;

    public int getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(int telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public Boolean getPolarNight() {
        return polarNight;
    }

    public void setPolarNight(Boolean polarNight) {
        this.polarNight = polarNight;
    }

    @Override
    protected void initDiscriminator() {
        countryDiscriminator = CountryDiscriminator.COLD;
    }

    @Override
    public String toString() {
        return super.toString() +
                "telephoneCode='" + telephoneCode + '\'' +
                ", polarNight=" + polarNight +
                '}';
    }
}
