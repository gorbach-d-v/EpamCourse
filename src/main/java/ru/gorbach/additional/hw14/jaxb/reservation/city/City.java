package ru.gorbach.additional.hw14.jaxb.reservation.city;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "population", "capital"}, name = "city")
public class City {
    private String name;
    private int population;
    private boolean isCapital;

    public City() {
    }

    public City(String name, int population, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    @Override
    public String toString() {
        return "City{" +
                ", name='" + name +
                ", population=" + population +
                ", isCapital=" + isCapital +
                '}';
    }
}
