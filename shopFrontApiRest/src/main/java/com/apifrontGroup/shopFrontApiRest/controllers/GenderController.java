package com.apifrontGroup.shopFrontApiRest.controllers;

import com.apifrontGroup.shopFrontApiRest.entity.Gender;
import com.apifrontGroup.shopFrontApiRest.service.GenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenderController {
    @Autowired
    GenderService genderService;

    @PostMapping("genders/save")
    Gender save(@Valid @RequestBody Gender gender){
        return genderService.saveGender(gender);
    }
}
