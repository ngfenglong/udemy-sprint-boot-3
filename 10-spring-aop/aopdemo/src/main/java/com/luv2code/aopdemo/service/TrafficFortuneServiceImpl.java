package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService{

    @Override
    public String getFortune() {
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String getFortune(boolean tripWire) {
        if(tripWire){
            throw new RuntimeException("Major accident! Highwway is closed!");
        }

        return getFortune();
    }
}
