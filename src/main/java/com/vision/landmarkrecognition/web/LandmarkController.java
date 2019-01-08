package com.vision.landmarkrecognition.web;

import com.vision.landmarkrecognition.service.ImageAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class LandmarkController {

    private final ImageAnalyzer imageAnalyzer;

    @Autowired
    private LandmarkController(ImageAnalyzer imageAnalyzer) {
        this.imageAnalyzer = imageAnalyzer;
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/upload")
    public String upload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addFlashAttribute("landmarkList", imageAnalyzer.detectLandmark(file));
        return "redirect:/result";
    }

    @GetMapping(value = "/result")
    public String result(Model model) {
        model.addAttribute("landmarkList", model.asMap().get("landmarkList"));
        return "result";
    }

}