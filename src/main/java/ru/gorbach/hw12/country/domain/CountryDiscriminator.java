package ru.gorbach.hw12.country.domain;

import java.util.HashMap;
import java.util.Map;

public enum CountryDiscriminator {
    HOT, COLD;

    static Map<String, CountryDiscriminator> stringModelDiscriminatorMap = new HashMap<>();

    static {
        for (CountryDiscriminator discriminator : CountryDiscriminator.values()) {
            stringModelDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static CountryDiscriminator getDiscriminatorByName(String discriminatorName) {
        return stringModelDiscriminatorMap.get(discriminatorName);
    }

    public static boolean isDiscriminatorExists(String discriminator) {
        return getDiscriminatorByName(discriminator) != null;
    }

    public static boolean isDiscriminatorNotExists(String discriminator) {
        return !isDiscriminatorExists(discriminator);
    }
}
