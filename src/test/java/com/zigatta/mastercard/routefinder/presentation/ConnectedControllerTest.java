package com.zigatta.mastercard.routefinder.presentation;

import com.zigatta.mastercard.routefinder.service.RouteFinderService;
import com.zigatta.mastercard.routefinder.util.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ConnectedControllerTest {
    private final ResponseEntity<String> yes = new ResponseEntity<>("yes", HttpStatus.OK);
    private final ResponseEntity<String> no = new ResponseEntity<>("no", HttpStatus.NOT_FOUND);
    private ConnectedController connectedController;
    @Mock
    private RouteFinderService routeFinderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        when(routeFinderService.connectionExists("A", "B")).thenReturn(true);
        when(routeFinderService.connectionExists("C", "D")).thenReturn(false);
        connectedController = new ConnectedController(routeFinderService);
    }

    @Test
    void whenEmptyInputThenNo() {
        ResponseEntity<String> actual = connectedController.connected(null, City.NEWARK.getName());
        assertEquals(no, actual);
        actual = connectedController.connected("", City.NEWARK.getName());
        assertEquals(no, actual);
        actual = connectedController.connected(" ", City.NEWARK.getName());
        assertEquals(no, actual);

        actual = connectedController.connected(City.ALBANY.getName(), null);
        assertEquals(no, actual);
        actual = connectedController.connected(City.ALBANY.getName(), "");
        assertEquals(no, actual);
        actual = connectedController.connected(City.ALBANY.getName(), " ");
        assertEquals(no, actual);
    }

    @Test
    void whenValidInputWithoutConnectionThenNo() {
        ResponseEntity<String> actual = connectedController.connected("C", "D");
        assertEquals(no, actual);
    }

    @Test
    void whenValidInputWithConnectionThenYes() {
        ResponseEntity<String> actual = connectedController.connected("A", "B");
        assertEquals(yes, actual);
    }
}