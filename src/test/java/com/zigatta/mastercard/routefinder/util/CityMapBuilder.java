package com.zigatta.mastercard.routefinder.util;

import com.zigatta.mastercard.routefinder.data.dto.CityMap;

public class CityMapBuilder {
    public CityMap buildCityMap() {
        CityMap cityMap = new CityMap();

        cityMap.addCity(City.ALBANY);
        cityMap.addCity(City.BOSTON);
        cityMap.addCity(City.NEWARK);
        cityMap.addCity(City.NEW_YORK);
        cityMap.addCity(City.PHILADELPHIA);
        cityMap.addCity(City.TRENTON);

        cityMap.addConnection(City.BOSTON, City.NEW_YORK);
        cityMap.addConnection(City.PHILADELPHIA, City.NEWARK);
        cityMap.addConnection(City.NEWARK, City.BOSTON);
        cityMap.addConnection(City.TRENTON, City.ALBANY);

        return cityMap;
    }
}
