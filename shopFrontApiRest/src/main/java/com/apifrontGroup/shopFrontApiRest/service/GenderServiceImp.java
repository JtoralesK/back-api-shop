package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.entity.Gender;
import com.apifrontGroup.shopFrontApiRest.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenderServiceImp implements GenderService{
    @Autowired
    GenderRepository genderRepository;

    @Override
    public List<Gender> findAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    public Gender saveGender(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(Long id, Gender gender) {
        return null;
    }

    @Override
    public void deleteGender(Long id) {

    }
}
