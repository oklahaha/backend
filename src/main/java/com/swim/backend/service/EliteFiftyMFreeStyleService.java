package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMFreeStyle;
import com.swim.backend.repository.EliteFiftyMFreeStyleRepository;

@Service
public class EliteFiftyMFreeStyleService {

    @Autowired
    private EliteFiftyMFreeStyleRepository eliteFiftyMFreeStyleRepository;

    public EliteFiftyMFreeStyle saveEliteFiftyMFreeStyle(EliteFiftyMFreeStyle eliteFiftyMFreeStyle) {
        return eliteFiftyMFreeStyleRepository.save(eliteFiftyMFreeStyle);
    }

    public List<EliteFiftyMFreeStyle> saveEliteFiftyMFreeStyleList(List<EliteFiftyMFreeStyle> eliteFiftyMFreeStyleList) {
        return eliteFiftyMFreeStyleRepository.saveAll(eliteFiftyMFreeStyleList);
    }
    
    public List<EliteFiftyMFreeStyle> listEliteFiftyMFreeStyle() {
        return eliteFiftyMFreeStyleRepository.findAll();
    }

    public List<EliteFiftyMFreeStyle> listEliteFiftyMFreeStyleByGenderAndAge(String gender, Integer age) {
        return eliteFiftyMFreeStyleRepository.findByGenderAndAgeOrderByTimeAsc(gender, age);
    }

    public EliteFiftyMFreeStyle getEliteFiftyMFreeStyle(String id) {
        return eliteFiftyMFreeStyleRepository.findById(id).orElse(null);
    }

    public EliteFiftyMFreeStyle saveOrUpdateEliteFiftyMFreeStyle(EliteFiftyMFreeStyle eliteFiftyMFreeStyle) {
        return eliteFiftyMFreeStyleRepository.save(eliteFiftyMFreeStyle);
    }

    @Transactional
    public String deleteEliteFiftyMFreeStyle(String id) {
        eliteFiftyMFreeStyleRepository.deleteById(id);
        return "EliteFiftyMFreeStyle removed";
    }

    @Transactional
    public String deleteEliteFiftyMFreeStyleByAthlete(Athlete athleteId) {
        eliteFiftyMFreeStyleRepository.deleteByAthleteId(athleteId);
        return "EliteFiftyMFreeStyle removed";
    }

    @Transactional
    public String deleteAllEliteFiftyMFreeStyle(List<String> ids) {
        eliteFiftyMFreeStyleRepository.deleteAllById(ids);
        return "EliteFiftyMFreeStyle entries removed";
    }
}