package com.zigatta.mastercard.routefinder.service;

import com.zigatta.mastercard.routefinder.data.CityMapRepository;
import com.zigatta.mastercard.routefinder.data.dto.CityMap;
import com.zigatta.mastercard.routefinder.util.City;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@Service
public class RouteFinderService {
    private final CityMap cityMap;

    public RouteFinderService(CityMapRepository cityMapRepository) {
        this.cityMap = cityMapRepository.getCityMap();
    }

    public boolean connectionExists(String city1Name, String city2Name) {
        if (StringUtils.isEmpty(city1Name) || StringUtils.isEmpty(city1Name.trim()) ||
                StringUtils.isEmpty(city2Name) || StringUtils.isEmpty(city2Name.trim())) {
            return false;
        }
        City city1 = City.cityFor(city1Name);
        City city2 = City.cityFor(city2Name);
        if (city1 == null || city2 == null) {
            // invalid City name specified; per requirements, returning false
            return false;
        }
        return deptFirstSearch(city1, city2);
    }

    private boolean deptFirstSearch(City city1, City city2) {
        Set<City> visited = new HashSet<>();
        Stack<City> toVisit = new Stack<>();
        toVisit.push(city1);
        while (!toVisit.isEmpty()) {
            City visiting = toVisit.pop();
            if (visiting.equals(city2)) {
                return true;
            }
            if (!visited.contains(visiting)) {
                visited.add(visiting);
                Set<City> connectionsForCity = cityMap.getConnectionsForCity(visiting);
                if (CollectionUtils.isEmpty(connectionsForCity)) {
                    continue;
                }
                for (City city : connectionsForCity) {
                    toVisit.push(city);
                }
            }
        }
        return false;
    }
}
