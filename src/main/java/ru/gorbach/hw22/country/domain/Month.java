package ru.gorbach.hw22.country.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    private static Map<String, Month> strNameEnumItemMap;

    static {
        strNameEnumItemMap = new HashMap<>();
        for (Month enumItem : Month.values()) {
            strNameEnumItemMap.put(enumItem.name(), enumItem);
        }
    }

    Month(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<Month> getMonthByStrValue(String monthStr) {
        return Optional.ofNullable(strNameEnumItemMap.get(monthStr));
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
