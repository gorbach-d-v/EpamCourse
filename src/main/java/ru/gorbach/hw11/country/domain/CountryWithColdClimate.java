package ru.gorbach.hw11.country.domain;

public class CountryWithColdClimate extends Country {
    private int telephoneCode;
    private Boolean polarNight;

    public CountryWithColdClimate() {
    }

    public CountryWithColdClimate(String name, String language) {
        super(name, language);
    }

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
        countryDiscriminator = CountryDiscriminator.HOT;
    }

    @Override
    public String toString() {
        return super.toString() +
                "telephoneCode='" + telephoneCode + '\'' +
                ", polarNight=" + polarNight +
                '}';
    }
}
