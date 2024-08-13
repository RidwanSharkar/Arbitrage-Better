/* src/main/java\com\rsharkar\arbitrager/ ArbitragerController.java */
package com.example.arbitrageapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArbitragerController 
{
    @GetMapping("/api/test")
    public String testAPI() 
    {
        return "API is working!";
    }
}