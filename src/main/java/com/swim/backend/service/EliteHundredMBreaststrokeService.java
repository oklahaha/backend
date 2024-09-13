package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMBreaststroke;
import com.swim.backend.repository.EliteHundredMBreaststrokeRepository;

@Service
public class EliteHundredMBreaststrokeService {

    @Autowired
    private EliteHundredMBreaststrokeRepository eliteHundredMBreaststrokeRepository;

    public EliteHundredMBreaststroke saveEliteHundredMBreaststroke(EliteHundredMBreaststroke eliteHundredMBreaststroke) {
        return eliteHundredMBreaststrokeRepository.save(eliteHundredMBreaststroke);
    }

    public List<EliteHundredMBreaststroke> saveEliteHundredMBreaststrokeList(List<EliteHundredMBreaststroke> eliteHundredMBreaststrokeList) {
        return eliteHundredMBreaststrokeRepository.saveAll(eliteHundredMBreaststrokeList);
    }
    
    public List<EliteHundredMBreaststroke> listEliteHundredMBreaststroke() {
        return eliteHundredMBreaststrokeRepository.findAll();
    }

    public List<EliteHundredMBreaststroke> listEliteHundredMBreaststrokeByGenderAndAge(String gender, Integer age) {
        return eliteHundredMBreaststrokeRepository.findByGenderAndAge(gender, age);
    }

    public EliteHundredMBreaststroke getEliteHundredMBreaststroke(String id) {
        return eliteHundredMBreaststrokeRepository.findById(id).orElse(null);
    }

    public EliteHundredMBreaststroke saveOrUpdateEliteHundredMBreaststroke(EliteHundredMBreaststroke eliteHundredMBreaststroke) {
        return eliteHundredMBreaststrokeRepository.save(eliteHundredMBreaststroke);
    }

    @Transactional
    public String deleteEliteHundredMBreaststroke(String id) {
        eliteHundredMBreaststrokeRepository.deleteById(id);
        return "EliteHundredMBreaststroke removed";
    }

    @Transactional
    public String deleteEliteHundredMBreaststrokeByAthlete(Athlete athleteId) {
        eliteHundredMBreaststrokeRepository.deleteByAthleteId(athleteId);
        return "EliteHundredMBreaststroke removed";
    }

    @Transactional
    public String deleteAllEliteHundredMBreaststroke(List<String> ids) {
        eliteHundredMBreaststrokeRepository.deleteAllById(ids);
        return "EliteHundredMBreaststroke entries removed";
    }
}