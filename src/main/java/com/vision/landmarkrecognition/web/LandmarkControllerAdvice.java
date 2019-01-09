package com.vision.landmarkrecognition.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class LandmarkControllerAdvice {

    @ExceptionHandler(Exception.class)
    @GetMapping(value = "/exception")
    public String exception(){
        return "exception";
    }

}
