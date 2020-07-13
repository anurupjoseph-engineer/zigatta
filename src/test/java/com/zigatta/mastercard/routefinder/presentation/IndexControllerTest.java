package com.zigatta.mastercard.routefinder.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndexControllerTest {
    private IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    void whenHomeThenRedirect() {
        String expected = "redirect:swagger-ui.html";
        String actual = indexController.home();
        assertEquals(expected, actual);
    }
}