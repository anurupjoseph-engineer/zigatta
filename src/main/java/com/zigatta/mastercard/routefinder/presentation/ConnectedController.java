package com.zigatta.mastercard.routefinder.presentation;

import com.zigatta.mastercard.routefinder.service.RouteFinderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedController {
    private final RouteFinderService routeFinderService;

    public ConnectedController(RouteFinderService routeFinderService) {
        this.routeFinderService = routeFinderService;
    }

    @ApiOperation(value = "Determine if two cities are connected.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found route from source to destination"),
            @ApiResponse(code = 404, message = "Found no route from source to destination")
    })
    @GetMapping(value = "/connected", produces = "text/plain")
    public ResponseEntity<String> connected(@ApiParam(value = "Origin City", required = true)
                                            @RequestParam String origin,
                                            @ApiParam(value = "Destination City", required = true)
                                            @RequestParam String destination) {
        ResponseEntity<String> yes = new ResponseEntity<>("yes", HttpStatus.OK);
        ResponseEntity<String> no = new ResponseEntity<>("no", HttpStatus.NOT_FOUND);
        if (StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
            return no;
        }
        boolean exists = routeFinderService.connectionExists(origin, destination);
        return exists ? yes : no;
    }
}
