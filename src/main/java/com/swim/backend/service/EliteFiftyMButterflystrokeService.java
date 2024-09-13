package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMButterflystroke;
import com.swim.backend.repository.EliteFiftyMButterflystrokeRepository;

@Service
public class EliteFiftyMButterflystrokeService {

    @Autowired
    private EliteFiftyMButterflystrokeRepository eliteFiftyMButterflystrokeRepository;

    public EliteFiftyMButterflystroke saveEliteFiftyMButterflystroke(EliteFiftyMButterflystroke eliteFiftyMButterflystroke) {
        return eliteFiftyMButterflystrokeRepository.save(eliteFiftyMButterflystroke);
    }

    public List<EliteFiftyMButterflystroke> saveEliteFiftyMButterflystrokeList(List<EliteFiftyMButterflystroke> eliteFiftyMButterflystrokeList) {
        return eliteFiftyMButterflystrokeRepository.saveAll(eliteFiftyMButterflystrokeList);
    }
    
    public List<EliteFiftyMButterflystroke> listEliteFiftyMButterflystroke() {
        return eliteFiftyMButterflystrokeRepository.findAll();
    }

    public List<EliteFiftyMButterflystroke> listEliteFiftyMButterflystrokeByGenderAndAge(String gender, Integer age) {
        return eliteFiftyMButterflystrokeRepository.findByGenderAndAge(gender, age);
    }

    public EliteFiftyMButterflystroke getEliteFiftyMButterflystroke(String id) {
        return eliteFiftyMButterflystrokeRepository.findById(id).orElse(null);
    }

    public EliteFiftyMButterflystroke saveOrUpdateEliteFiftyMButterflystroke(EliteFiftyMButterflystroke eliteFiftyMButterflystroke) {
        return eliteFiftyMButterflystrokeRepository.save(eliteFiftyMButterflystroke);
    }

    @Transactional
    public String deleteEliteFiftyMButterflystroke(String id) {
        eliteFiftyMButterflystrokeRepository.deleteById(id);
        return "EliteFiftyMButterflystroke removed";
    }

    @Transactional
    public String deleteEliteFiftyMButterflystrokeByAthlete(Athlete athleteId) {
        eliteFiftyMButterflystrokeRepository.deleteByAthleteId(athleteId);
        return "EliteFiftyMButterflystroke removed";
    }

    @Transactional
    public String deleteAllEliteFiftyMButterflystroke(List<String> ids) {
        eliteFiftyMButterflystrokeRepository.deleteAllById(ids);
        return "EliteFiftyMButterflystroke entries removed";
    }
}