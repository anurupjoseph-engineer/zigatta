package com.zigatta.mastercard.routefinder.domain;

import com.zigatta.mastercard.routefinder.data.dto.CityMap;
import com.zigatta.mastercard.routefinder.util.City;
import com.zigatta.mastercard.routefinder.util.CityMapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityMapTest {
    private CityMap cityMap;

    @BeforeEach
    void setUp() {
        CityMapBuilder cityMapBuilder = new CityMapBuilder();
        cityMap = cityMapBuilder.buildCityMap();
        cityMap.addConnection(City.TRENTON, City.ALBANY);
    }

    @Test
    void removeCity() {
    }

    @Test
    void getConnectionsForAlbany() {
        Set<City> expected = Set.of(City.TRENTON);
        Set<City> actual = cityMap.getConnectionsForCity(City.ALBANY);
        assertEquals(expected, actual);
    }

    @Test
    void getConnectionsForBoston() {
        Set<City> expected = Set.of(City.NEWARK, City.NEW_YORK);
        Set<City> actual = cityMap.getConnectionsForCity(City.BOSTON);
        assertEquals(expected, actual);
    }

    @Test
    void getConnectionsForNewark() {
        Set<City> expected = Set.of(City.BOSTON, City.PHILADELPHIA);
        Set<City> actual = cityMap.getConnectionsForCity(City.NEWARK);
        assertEquals(expected, actual);
    }

    @Test
    void getConnectionsForNewYork() {
        Set<City> expected = Set.of(City.BOSTON);
        Set<City> actual = cityMap.getConnectionsForCity(City.NEW_YORK);
        assertEquals(expected, actual);
    }

    @Test
    void getConnectionsForPhiladelphia() {
        Set<City> expected = Set.of(City.NEWARK);
        Set<City> actual = cityMap.getConnectionsForCity(City.PHILADELPHIA);
        assertEquals(expected, actual);
    }

    @Test
    void getConnectionsForTrenton() {
        Set<City> expected = Set.of(City.ALBANY);
        Set<City> actual = cityMap.getConnectionsForCity(City.TRENTON);
        assertEquals(expected, actual);
    }
}