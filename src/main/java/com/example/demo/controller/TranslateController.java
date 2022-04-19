package com.example.demo.controller;

import com.example.demo.models.LanguageResponse;
import com.example.demo.services.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/translate/")
@CrossOrigin()
public class TranslateController {

    @Autowired
    TranslateService service;

    @GetMapping("get/languages")
    public List<LanguageResponse> getAllLanguages(){
        return service.getAllLanguages();
    }
}
