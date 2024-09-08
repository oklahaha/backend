package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBreaststroke;
import com.swim.backend.repository.FiftyMBreaststrokeRepository;

@Service
public class FiftyMBreaststrokeService {

    @Autowired
    private FiftyMBreaststrokeRepository fiftyMBreaststrokeRepository;

    public FiftyMBreaststroke saveFiftyMBreaststroke(FiftyMBreaststroke fiftyMBreaststroke) {
        return fiftyMBreaststrokeRepository.save(fiftyMBreaststroke);
    }

    public List<FiftyMBreaststroke> saveFiftyMBreaststrokes(List<FiftyMBreaststroke> fiftyMBreaststrokes) {
        return fiftyMBreaststrokeRepository.saveAll(fiftyMBreaststrokes);
    }
    
    public List<FiftyMBreaststroke> listFiftyMBreaststroke() {
        return fiftyMBreaststrokeRepository.findAll();
    }

    public FiftyMBreaststroke getFiftyMBreaststroke(String id) {
        return fiftyMBreaststrokeRepository.findById(id).orElse(null);
    }

    public FiftyMBreaststroke saveOrUpdateFiftyMBreaststroke(FiftyMBreaststroke fiftyMBreaststroke) {
        return fiftyMBreaststrokeRepository.save(fiftyMBreaststroke);
    }

    @Transactional
    public String deleteFiftyMBreaststroke(String id){
        fiftyMBreaststrokeRepository.deleteById(id);
        return "FiftyMBreaststroke removed";
    }

    @Transactional
    public String deleteFiftyMBreaststrokeByAthlete(Athlete athleteId) {
        fiftyMBreaststrokeRepository.deleteByAthleteId(athleteId);
        return "FiftyMBreaststroke removed";
    }

    @Transactional
    public String deleteAllFiftyMBreaststroke(List<String> ids) {
        fiftyMBreaststrokeRepository.deleteAllById(ids);
        return "FiftyMBreaststroke entries removed";
    }
}