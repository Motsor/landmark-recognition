package com.vision.landmarkrecognition.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandmarkErrorController implements ErrorController {

    @GetMapping(value = "/error")
    public String error(Exception e) {
        e.printStackTrace();
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}