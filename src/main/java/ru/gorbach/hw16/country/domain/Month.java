package ru.gorbach.hw16.country.domain;

public enum Month {
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    private String description;

    Month(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isStrBelongsToEnumValues(String s) {
        for (Month month : Month.values()) {
            if (month.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
