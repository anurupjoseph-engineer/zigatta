package com.zigatta.mastercard.routefinder.presentation;

import com.zigatta.mastercard.routefinder.service.RouteFinderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class ConnectedControllerIntTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteFinderService routeFinderService;

    private final String baseUrl = "/connected?origin=%s&destination=%s";

    @BeforeEach
    void setUp() {
        when(routeFinderService.connectionExists("A", "B")).thenReturn(true);
        when(routeFinderService.connectionExists("C", "D")).thenReturn(false);
    }

    @Test
    void whenRouteThenYes() throws Exception {
        String url = String.format(baseUrl, "A", "B");
        this.mockMvc.perform(get(url))
                .andExpect(content().string(containsString("yes")));
    }

    @Test
    void whenNoRouteThenNo() throws Exception {
        String url = String.format(baseUrl, "C", "D");
        this.mockMvc.perform(get(url))
                .andExpect(content().string(containsString("no")));
    }
}
