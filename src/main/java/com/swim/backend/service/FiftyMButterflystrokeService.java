package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMButterflystroke;
import com.swim.backend.repository.FiftyMButterflystrokeRepository;

@Service
public class FiftyMButterflystrokeService {

    @Autowired
    private FiftyMButterflystrokeRepository fiftyMButterflystrokeRepository;

    public FiftyMButterflystroke saveFiftyMButterflystroke(FiftyMButterflystroke fiftyMButterflystroke) {
        return fiftyMButterflystrokeRepository.save(fiftyMButterflystroke);
    }

    public List<FiftyMButterflystroke> saveFiftyMButterflystrokeList(List<FiftyMButterflystroke> fiftyMButterflystrokeList) {
        return fiftyMButterflystrokeRepository.saveAll(fiftyMButterflystrokeList);
    }
    
    public List<FiftyMButterflystroke> listFiftyMButterflystroke() {
        return fiftyMButterflystrokeRepository.findAll();
    }

    public List<FiftyMButterflystroke> listFiftyMButterflystrokeByGenderAndAge(String gender, Integer age) {
        return fiftyMButterflystrokeRepository.findByGenderAndAge(gender, age);
    }

    public FiftyMButterflystroke getFiftyMButterflystroke(String id) {
        return fiftyMButterflystrokeRepository.findById(id).orElse(null);
    }

    public FiftyMButterflystroke saveOrUpdateFiftyMButterflystroke(FiftyMButterflystroke fiftyMButterflystroke) {
        return fiftyMButterflystrokeRepository.save(fiftyMButterflystroke);
    }

    @Transactional
    public String deleteFiftyMButterflystroke(String id) {
        fiftyMButterflystrokeRepository.deleteById(id);
        return "FiftyMButterflystroke removed";
    }

    @Transactional
    public String deleteFiftyMButterflystrokeByAthlete(Athlete athleteId) {
        fiftyMButterflystrokeRepository.deleteByAthleteId(athleteId);
        return "FiftyMButterflystroke removed";
    }

    @Transactional
    public String deleteAllFiftyMButterflystroke(List<String> ids) {
        fiftyMButterflystrokeRepository.deleteAllById(ids);
        return "FiftyMButterflystroke entries removed";
    }
}