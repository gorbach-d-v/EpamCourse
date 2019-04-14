package ru.gorbach.hw20.country.dto;

import ru.gorbach.hw20.country.domain.CountryDiscriminator;
import ru.gorbach.hw20.country.domain.Month;

public class CountryWithHotClimateDto extends CountryDto {
    private Month hottestMonth;
    private Double averageTemp;

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
