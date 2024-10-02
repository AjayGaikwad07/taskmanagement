package com.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    // Map the root URL ("/") to the landing page
    @GetMapping("/")
    public String showLandingPage(){
        return "landing";   // Render the landing.html template
    }

    
}
