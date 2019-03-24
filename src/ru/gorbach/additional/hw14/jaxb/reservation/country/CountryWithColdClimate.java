package ru.gorbach.additional.hw14.jaxb.reservation.country;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"telephoneCode", "polarNight"})
public class CountryWithColdClimate extends Country {
    private int telephoneCode;
    private Boolean polarNight;

    public CountryWithColdClimate() {
        super();
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
