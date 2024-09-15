package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMFreestyle;
import com.swim.backend.repository.EliteHundredMFreestyleRepository;

@Service
public class EliteHundredMFreestyleService {

    @Autowired
    private EliteHundredMFreestyleRepository eliteHundredMFreestyleRepository;

    public EliteHundredMFreestyle saveEliteHundredMFreestyle(EliteHundredMFreestyle eliteHundredMFreestyle) {
        return eliteHundredMFreestyleRepository.save(eliteHundredMFreestyle);
    }

    public List<EliteHundredMFreestyle> saveEliteHundredMFreestyleList(List<EliteHundredMFreestyle> eliteHundredMFreestyleList) {
        return eliteHundredMFreestyleRepository.saveAll(eliteHundredMFreestyleList);
    }
    
    public List<EliteHundredMFreestyle> listEliteHundredMFreestyle() {
        return eliteHundredMFreestyleRepository.findAll();
    }

    public List<EliteHundredMFreestyle> listEliteHundredMFreestyleByGenderAndAge(String gender, Integer age) {
        return eliteHundredMFreestyleRepository.findByGenderAndAgeOrderByTimeAsc(gender, age);
    }

    public EliteHundredMFreestyle getEliteHundredMFreestyle(String id) {
        return eliteHundredMFreestyleRepository.findById(id).orElse(null);
    }

    public EliteHundredMFreestyle saveOrUpdateEliteHundredMFreestyle(EliteHundredMFreestyle eliteHundredMFreestyle) {
        return eliteHundredMFreestyleRepository.save(eliteHundredMFreestyle);
    }

    @Transactional
    public String deleteEliteHundredMFreestyle(String id) {
        eliteHundredMFreestyleRepository.deleteById(id);
        return "EliteHundredMFreestyle removed";
    }

    @Transactional
    public String deleteEliteHundredMFreestyleByAthlete(Athlete athleteId) {
        eliteHundredMFreestyleRepository.deleteByAthleteId(athleteId);
        return "EliteHundredMFreestyle removed";
    }

    @Transactional
    public String deleteAllEliteHundredMFreestyle(List<String> ids) {
        eliteHundredMFreestyleRepository.deleteAllById(ids);
        return "EliteHundredMFreestyle entries removed";
    }
}