package com.zigatta.mastercard.routefinder.util;

public enum City {
    ALBANY("Albany"),
    BOSTON("Boston"),
    NEWARK("Newark"),
    NEW_YORK("New York"),
    PHILADELPHIA("Philadelphia"),
    TRENTON("TRENTON");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public static City cityFor(String name) {
        String modifiedName = name.trim().replace(' ', '_');
        City city;
        try {
            city = valueOf(modifiedName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            city = null;
        }
        return city;
    }

    public String getName() {
        return name;
    }
}
