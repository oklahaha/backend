package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBackstroke;
import com.swim.backend.repository.FiftyMBackstrokeRepository;


@Service
public class FiftyMBackstrokeService {

    @Autowired
    private FiftyMBackstrokeRepository fiftyMBackstrokeRepository;

    public FiftyMBackstroke saveFiftyMBackstroke(FiftyMBackstroke fiftyMBackstroke) {
        return fiftyMBackstrokeRepository.save(fiftyMBackstroke);
    }

    public List<FiftyMBackstroke> saveFiftyMBackstrokes(List<FiftyMBackstroke> fiftyMBackstrokes) {
        return fiftyMBackstrokeRepository.saveAll(fiftyMBackstrokes);
    }
    
    public List<FiftyMBackstroke> listFiftyMBackstroke() {
        return fiftyMBackstrokeRepository.findAll();
    }

    public FiftyMBackstroke getfiftyMBackstroke(String id) {
        return fiftyMBackstrokeRepository.findById(id).orElse(null);
    }

    public FiftyMBackstroke saveOrUpdateFiftyMBackstroke(FiftyMBackstroke fiftyMBackstroke) {
        return fiftyMBackstrokeRepository.save(fiftyMBackstroke);
    }

    @Transactional
    public String deleteFiftyMBackstroke(String id){
        fiftyMBackstrokeRepository.deleteById(id);
        return "FiftyMBackstroke removed";
    }

    @Transactional
    public String deleteFiftyMBackstrokeByAthlete(Athlete athleteId) {
        fiftyMBackstrokeRepository.deleteByAthleteId(athleteId);
        return "FiftyMBackstroke removed";
    }

    @Transactional
    public String deleteAllFiftyMBackstroke(List<String> id) {
        fiftyMBackstrokeRepository.deleteAllById(id);
        return "FiftyMBackstroke removed";
    }
}
