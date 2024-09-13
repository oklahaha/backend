package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBackstroke;
import com.swim.backend.repository.EliteFiftyMBackstrokeRepository;


@Service
public class EliteFiftyMBackstrokeService {

    @Autowired
    private EliteFiftyMBackstrokeRepository eliteFiftyMBackstrokeRepository;

    public EliteFiftyMBackstroke saveEliteFiftyMBackstroke(EliteFiftyMBackstroke eliteFiftyMBackstroke) {
        return eliteFiftyMBackstrokeRepository.save(eliteFiftyMBackstroke);
    }

    public List<EliteFiftyMBackstroke> saveEliteFiftyMBackstrokes(List<EliteFiftyMBackstroke> eliteFiftyMBackstrokes) {
        return eliteFiftyMBackstrokeRepository.saveAll(eliteFiftyMBackstrokes);
    }
    
    public List<EliteFiftyMBackstroke> listEliteFiftyMBackstroke() {
        return eliteFiftyMBackstrokeRepository.findAll();
    }

    public List<EliteFiftyMBackstroke> listEliteFiftyMBackstrokeByGenderAndAge(String gender, Integer age) {
        return eliteFiftyMBackstrokeRepository.findByGenderAndAge(gender, age);
    }

    public EliteFiftyMBackstroke geteliteFiftyMBackstroke(String id) {
        return eliteFiftyMBackstrokeRepository.findById(id).orElse(null);
    }

    public EliteFiftyMBackstroke saveOrUpdateEliteFiftyMBackstroke(EliteFiftyMBackstroke eliteFiftyMBackstroke) {
        return eliteFiftyMBackstrokeRepository.save(eliteFiftyMBackstroke);
    }

    @Transactional
    public String deleteEliteFiftyMBackstroke(String id){
        eliteFiftyMBackstrokeRepository.deleteById(id);
        return "EliteFiftyMBackstroke removed";
    }

    @Transactional
    public String deleteEliteFiftyMBackstrokeByAthlete(Athlete athleteId) {
        eliteFiftyMBackstrokeRepository.deleteByAthleteId(athleteId);
        return "EliteFiftyMBackstroke removed";
    }

    @Transactional
    public String deleteAllEliteFiftyMBackstroke(List<String> id) {
        eliteFiftyMBackstrokeRepository.deleteAllById(id);
        return "EliteFiftyMBackstroke removed";
    }
}
