package com.zigatta.mastercard.routefinder.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
