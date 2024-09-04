package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swim.backend.model.Athlete;
import com.swim.backend.repository.AthleteRepository;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public List<Athlete> saveAthletes(List<Athlete> athletes) {
        return athleteRepository.saveAll(athletes);
    }
    
    public List<Athlete> listAthlete() {
        return athleteRepository.findAll();
    }

    public Athlete getAthlete(Integer athleteId) {
        return athleteRepository.findByAthleteId(athleteId);
    }

    public Athlete saveOrUpdateAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public String deleteAthlete(Integer athleteId){
        athleteRepository.deleteById(athleteId);
        return "Athlete removed";
    }

    public String deleteAllAthlete(List<Integer> athleteIds) {
        athleteRepository.deleteAllById(athleteIds);
        return "Athlete removed";
    }
}
