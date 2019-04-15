package ru.gorbach.hw22.country.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CountryDiscriminator {
    HOT, COLD;

    static Map<String, CountryDiscriminator> stringModelDiscriminatorMap = new HashMap<>();

    static {
        for (CountryDiscriminator discriminator : CountryDiscriminator.values()) {
            stringModelDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static Optional<CountryDiscriminator> getDiscriminatorByName(String discriminatorName) {
        return Optional.ofNullable(stringModelDiscriminatorMap.get(discriminatorName));
    }
}
