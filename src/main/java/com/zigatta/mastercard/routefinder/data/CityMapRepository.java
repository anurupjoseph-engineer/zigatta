package com.zigatta.mastercard.routefinder.data;

import com.zigatta.mastercard.routefinder.data.dto.CityMap;
import com.zigatta.mastercard.routefinder.util.City;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Repository
public class CityMapRepository {
    private CityMap cityMap;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {
        cityMap = new CityMap();
        URL resource = getClass().getClassLoader().getResource("city.txt");
        Path path = Paths.get(Objects.requireNonNull(resource).toURI());
        List<String> connections = Files.readAllLines(path);
        for (String connection : connections) {
            String[] connectionArr = connection.split(",");
            String originName = connectionArr[0];
            String destinationName = connectionArr[1];
            City origin = City.cityFor(originName);
            cityMap.addCity(origin);
            City destination = City.cityFor(destinationName);
            cityMap.addCity(destination);
            cityMap.addConnection(origin, destination);
        }
    }

    public CityMap getCityMap() {
        return cityMap;
    }
}
