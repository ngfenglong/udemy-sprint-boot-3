package com.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!!!!";
    }

    // expose new endpoint for "workout"

    @GetMapping("/workout")
    public String getDailtyWorkout() {
        return "Run a hard 5k!";
    }

    @GetMapping("/workout2")
    public String getDailtyWorkout2() {
        return "Run a hard 5k!";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}