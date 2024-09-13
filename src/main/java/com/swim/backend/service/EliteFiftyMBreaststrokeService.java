package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBreaststroke;
import com.swim.backend.repository.EliteFiftyMBreaststrokeRepository;

@Service
public class EliteFiftyMBreaststrokeService {

    @Autowired
    private EliteFiftyMBreaststrokeRepository eliteFiftyMBreaststrokeRepository;

    public EliteFiftyMBreaststroke saveEliteFiftyMBreaststroke(EliteFiftyMBreaststroke eliteFiftyMBreaststroke) {
        return eliteFiftyMBreaststrokeRepository.save(eliteFiftyMBreaststroke);
    }

    public List<EliteFiftyMBreaststroke> saveEliteFiftyMBreaststrokes(List<EliteFiftyMBreaststroke> eliteFiftyMBreaststrokes) {
        return eliteFiftyMBreaststrokeRepository.saveAll(eliteFiftyMBreaststrokes);
    }
    
    public List<EliteFiftyMBreaststroke> listEliteFiftyMBreaststroke() {
        return eliteFiftyMBreaststrokeRepository.findAll();
    }

    public List<EliteFiftyMBreaststroke> listEliteFiftyMBreaststrokeByGenderAndAge(String gender, Integer age) {
        return eliteFiftyMBreaststrokeRepository.findByGenderAndAge(gender, age);
    }

    public EliteFiftyMBreaststroke getEliteFiftyMBreaststroke(String id) {
        return eliteFiftyMBreaststrokeRepository.findById(id).orElse(null);
    }

    public EliteFiftyMBreaststroke saveOrUpdateEliteFiftyMBreaststroke(EliteFiftyMBreaststroke eliteFiftyMBreaststroke) {
        return eliteFiftyMBreaststrokeRepository.save(eliteFiftyMBreaststroke);
    }

    @Transactional
    public String deleteEliteFiftyMBreaststroke(String id){
        eliteFiftyMBreaststrokeRepository.deleteById(id);
        return "EliteFiftyMBreaststroke removed";
    }

    @Transactional
    public String deleteEliteFiftyMBreaststrokeByAthlete(Athlete athleteId) {
        eliteFiftyMBreaststrokeRepository.deleteByAthleteId(athleteId);
        return "EliteFiftyMBreaststroke removed";
    }

    @Transactional
    public String deleteAllEliteFiftyMBreaststroke(List<String> ids) {
        eliteFiftyMBreaststrokeRepository.deleteAllById(ids);
        return "EliteFiftyMBreaststroke entries removed";
    }
}