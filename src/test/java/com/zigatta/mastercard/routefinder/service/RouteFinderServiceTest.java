package com.zigatta.mastercard.routefinder.service;

import com.zigatta.mastercard.routefinder.data.CityMapRepository;
import com.zigatta.mastercard.routefinder.data.dto.CityMap;
import com.zigatta.mastercard.routefinder.util.City;
import com.zigatta.mastercard.routefinder.util.CityMapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RouteFinderServiceTest {
    @Mock
    private CityMapRepository cityMapRepository;

    private RouteFinderService routeFinderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        CityMapBuilder cityMapBuilder = new CityMapBuilder();
        CityMap cityMap = cityMapBuilder.buildCityMap();
        when(cityMapRepository.getCityMap()).thenReturn(cityMap);

        routeFinderService = new RouteFinderService(cityMapRepository);
    }

    @Test
    void whenInputEmptyThenFalse() {
        boolean actual = routeFinderService.connectionExists(null, City.NEWARK.getName());
        assertFalse(actual);
        actual = routeFinderService.connectionExists("", City.NEWARK.getName());
        assertFalse(actual);
        actual = routeFinderService.connectionExists(" ", City.NEWARK.getName());
        assertFalse(actual);

        actual = routeFinderService.connectionExists(City.ALBANY.getName(), null);
        assertFalse(actual);
        actual = routeFinderService.connectionExists(City.ALBANY.getName(), "");
        assertFalse(actual);
        actual = routeFinderService.connectionExists(City.ALBANY.getName(), " ");
        assertFalse(actual);
    }

    @Test
    void whenInputInvalidThenFalse() {
        boolean actual = routeFinderService.connectionExists("A", City.NEWARK.getName());
        assertFalse(actual);
        actual = routeFinderService.connectionExists(City.BOSTON.getName(), "B");
        assertFalse(actual);
    }

    @Test
    void whenBostonNewarkThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.BOSTON.getName(), City.NEWARK.getName());
        assertTrue(actual);
    }

    @Test
    void whenBostonNewYorkThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.BOSTON.getName(), City.NEW_YORK.getName());
        assertTrue(actual);
    }

    @Test
    void whenBostonPhiladelphiaThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.BOSTON.getName(), City.PHILADELPHIA.getName());
        assertTrue(actual);
    }

    @Test
    void whenNewarkNewYorkThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.NEWARK.getName(), City.NEW_YORK.getName());
        assertTrue(actual);
    }

    @Test
    void whenNewarkPhiladelphiaThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.NEWARK.getName(), City.PHILADELPHIA.getName());
        assertTrue(actual);
    }

    @Test
    void whenNewYorkPhiladelphiaThenTrue() {
        boolean actual = routeFinderService.connectionExists(City.NEW_YORK.getName(), City.PHILADELPHIA.getName());
        assertTrue(actual);
    }

    @Test
    void connectionDoesNotExistBostonAlbany() {
        boolean actual = routeFinderService.connectionExists(City.BOSTON.getName(), City.ALBANY.getName());
        assertFalse(actual);
    }

    @Test
    void connectionDoesNotExistBostonTrenton() {
        boolean actual = routeFinderService.connectionExists(City.BOSTON.getName(), City.TRENTON.getName());
        assertFalse(actual);
    }

    @Test
    void connectionDoesNotExistPhiladelphiaAlbany() {
        boolean actual = routeFinderService.connectionExists(City.PHILADELPHIA.getName(), City.ALBANY.getName());
        assertFalse(actual);
    }
}