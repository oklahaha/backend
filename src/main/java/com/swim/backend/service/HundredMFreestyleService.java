package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMFreestyle;
import com.swim.backend.repository.HundredMFreestyleRepository;

@Service
public class HundredMFreestyleService {

    @Autowired
    private HundredMFreestyleRepository hundredMFreestyleRepository;

    public HundredMFreestyle saveHundredMFreestyle(HundredMFreestyle hundredMFreestyle) {
        return hundredMFreestyleRepository.save(hundredMFreestyle);
    }

    public List<HundredMFreestyle> saveHundredMFreestyleList(List<HundredMFreestyle> hundredMFreestyleList) {
        return hundredMFreestyleRepository.saveAll(hundredMFreestyleList);
    }
    
    public List<HundredMFreestyle> listHundredMFreestyle() {
        return hundredMFreestyleRepository.findAll();
    }

    public HundredMFreestyle getHundredMFreestyle(String id) {
        return hundredMFreestyleRepository.findById(id).orElse(null);
    }

    public HundredMFreestyle saveOrUpdateHundredMFreestyle(HundredMFreestyle hundredMFreestyle) {
        return hundredMFreestyleRepository.save(hundredMFreestyle);
    }

    @Transactional
    public String deleteHundredMFreestyle(String id) {
        hundredMFreestyleRepository.deleteById(id);
        return "HundredMFreestyle removed";
    }

    @Transactional
    public String deleteHundredMFreestyleByAthlete(Athlete athleteId) {
        hundredMFreestyleRepository.deleteByAthleteId(athleteId);
        return "HundredMFreestyle removed";
    }

    @Transactional
    public String deleteAllHundredMFreestyle(List<String> ids) {
        hundredMFreestyleRepository.deleteAllById(ids);
        return "HundredMFreestyle entries removed";
    }
}