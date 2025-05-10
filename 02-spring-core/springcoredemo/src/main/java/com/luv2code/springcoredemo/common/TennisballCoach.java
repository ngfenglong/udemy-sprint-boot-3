package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
public class TennisballCoach implements Coach{
    public TennisballCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volleyÏ";
    }
}
