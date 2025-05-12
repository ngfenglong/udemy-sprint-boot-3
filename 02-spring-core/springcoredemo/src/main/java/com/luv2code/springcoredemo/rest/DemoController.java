package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach myCoach;
    // private Coach anotherCoach;

    // Constructor injection method
    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach
                          // @Qualifier("cricketCoach") Coach theAnotherCoach
    ) {
        // Not recommended to have both @Qualifier and @Primary to be used in a project

        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
        // anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getWorkoutDaily() {
        return myCoach.getDailyWorkout();
    }

//    @GetMapping("/check")
//    public String check() {
//        return "Comparing beans: my coach == anotherCoach, " + (myCoach == anotherCoach);
//    }


//    This is Setter Injection example
//    public void setCoach(Coach theCoach){
//        myCoach = theCoach;
//    }

}
