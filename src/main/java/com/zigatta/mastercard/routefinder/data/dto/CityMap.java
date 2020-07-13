package com.zigatta.mastercard.routefinder.data.dto;

import com.zigatta.mastercard.routefinder.util.City;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class CityMap {
    private final Map<City, Set<City>> connectedCities = new HashMap<>();

    public void addCity(City city) {
        connectedCities.putIfAbsent(city, new HashSet<>());
    }

    public void addConnection(City city1, City city2) {
        Set<City> city1Connections = connectedCities.get(city1);
        Set<City> city2connections = connectedCities.get(city2);

        // a connection between two cities means that each city is connected to the other
        city1Connections.add(city2);
        city2connections.add(city1);
    }

    public Set<City> getConnectionsForCity(City city) {
        return connectedCities.get(city);
    }
}
