package ru.gorbach.hw19.country.domain;

public class CountryWithHotClimate extends Country {
    private Month hottestMonth;
    private Double averageTemp;

    public CountryWithHotClimate() {
        super();
    }

    public CountryWithHotClimate(String name, String language) {
        super(name, language);
    }

    public Month getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(Month hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public Double getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(Double averageTemp) {
        this.averageTemp = averageTemp;
    }

    @Override
    protected void initDiscriminator() {
        countryDiscriminator = CountryDiscriminator.HOT;
    }

    @Override
    public String toString() {
        return super.toString() +
                "hottestMonth=" + hottestMonth.getDescription() +
                ", averageTemp=" + averageTemp +
                '}';
    }
}
