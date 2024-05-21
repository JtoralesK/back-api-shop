package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.entity.Gender;

import java.util.List;

public interface GenderService {
    List<Gender> findAllGenders();
    Gender saveGender(Gender gender);
    Gender updateGender(Long id,Gender gender);
    void deleteGender(Long id);
}
