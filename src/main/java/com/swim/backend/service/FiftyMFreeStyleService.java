package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMFreeStyle;
import com.swim.backend.repository.FiftyMFreeStyleRepository;

@Service
public class FiftyMFreeStyleService {

    @Autowired
    private FiftyMFreeStyleRepository fiftyMFreeStyleRepository;

    public FiftyMFreeStyle saveFiftyMFreeStyle(FiftyMFreeStyle fiftyMFreeStyle) {
        return fiftyMFreeStyleRepository.save(fiftyMFreeStyle);
    }

    public List<FiftyMFreeStyle> saveFiftyMFreeStyleList(List<FiftyMFreeStyle> fiftyMFreeStyleList) {
        return fiftyMFreeStyleRepository.saveAll(fiftyMFreeStyleList);
    }
    
    public List<FiftyMFreeStyle> listFiftyMFreeStyle() {
        return fiftyMFreeStyleRepository.findAll();
    }

    public List<FiftyMFreeStyle> listFiftyMFreeStyleByGenderAndAge(String gender, Integer age) {
        return fiftyMFreeStyleRepository.findByGenderAndAgeOrderByTimeAsc(gender, age);
    }

    public FiftyMFreeStyle getFiftyMFreeStyle(String id) {
        return fiftyMFreeStyleRepository.findById(id).orElse(null);
    }

    public FiftyMFreeStyle saveOrUpdateFiftyMFreeStyle(FiftyMFreeStyle fiftyMFreeStyle) {
        return fiftyMFreeStyleRepository.save(fiftyMFreeStyle);
    }

    @Transactional
    public String deleteFiftyMFreeStyle(String id) {
        fiftyMFreeStyleRepository.deleteById(id);
        return "FiftyMFreeStyle removed";
    }

    @Transactional
    public String deleteFiftyMFreeStyleByAthlete(Athlete athleteId) {
        fiftyMFreeStyleRepository.deleteByAthleteId(athleteId);
        return "FiftyMFreeStyle removed";
    }

    @Transactional
    public String deleteAllFiftyMFreeStyle(List<String> ids) {
        fiftyMFreeStyleRepository.deleteAllById(ids);
        return "FiftyMFreeStyle entries removed";
    }
}