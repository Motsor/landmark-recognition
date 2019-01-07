package com.vision.landmarkrecognition.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandmarkController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

}
