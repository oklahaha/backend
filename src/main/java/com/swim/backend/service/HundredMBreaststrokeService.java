package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMBreaststroke;
import com.swim.backend.repository.HundredMBreaststrokeRepository;

@Service
public class HundredMBreaststrokeService {

    @Autowired
    private HundredMBreaststrokeRepository hundredMBreaststrokeRepository;

    public HundredMBreaststroke saveHundredMBreaststroke(HundredMBreaststroke hundredMBreaststroke) {
        return hundredMBreaststrokeRepository.save(hundredMBreaststroke);
    }

    public List<HundredMBreaststroke> saveHundredMBreaststrokeList(List<HundredMBreaststroke> hundredMBreaststrokeList) {
        return hundredMBreaststrokeRepository.saveAll(hundredMBreaststrokeList);
    }
    
    public List<HundredMBreaststroke> listHundredMBreaststroke() {
        return hundredMBreaststrokeRepository.findAll();
    }

    public List<HundredMBreaststroke> listHundredMBreaststrokeByGenderAndAge(String gender, Integer age) {
        return hundredMBreaststrokeRepository.findByGenderAndAge(gender, age);
    }

    public HundredMBreaststroke getHundredMBreaststroke(String id) {
        return hundredMBreaststrokeRepository.findById(id).orElse(null);
    }

    public HundredMBreaststroke saveOrUpdateHundredMBreaststroke(HundredMBreaststroke hundredMBreaststroke) {
        return hundredMBreaststrokeRepository.save(hundredMBreaststroke);
    }

    @Transactional
    public String deleteHundredMBreaststroke(String id) {
        hundredMBreaststrokeRepository.deleteById(id);
        return "HundredMBreaststroke removed";
    }

    @Transactional
    public String deleteHundredMBreaststrokeByAthlete(Athlete athleteId) {
        hundredMBreaststrokeRepository.deleteByAthleteId(athleteId);
        return "HundredMBreaststroke removed";
    }

    @Transactional
    public String deleteAllHundredMBreaststroke(List<String> ids) {
        hundredMBreaststrokeRepository.deleteAllById(ids);
        return "HundredMBreaststroke entries removed";
    }
}