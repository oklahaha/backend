package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.repository.AthleteRepository;
import com.swim.backend.repository.FiftyMBackstrokeRepository;
import com.swim.backend.repository.FiftyMBreaststrokeRepository;
import com.swim.backend.repository.FiftyMButterflystrokeRepository;
import com.swim.backend.repository.FiftyMFreeStyleRepository;
import com.swim.backend.repository.FiftyMKickBoardRepository;
import com.swim.backend.repository.HundredMBreaststrokeRepository;
import com.swim.backend.repository.HundredMFreestyleRepository;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private FiftyMBackstrokeRepository fiftyMBackstrokeRepository;

    @Autowired
    private FiftyMBreaststrokeRepository fiftyMBreaststrokeRepository;

    @Autowired
    private FiftyMButterflystrokeRepository fiftyMButterflystrokeRepository;

    @Autowired
    private FiftyMFreeStyleRepository fiftyMFreeStyleRepository;

    @Autowired
    private FiftyMKickBoardRepository fiftyMKickBoardRepository;

    @Autowired
    private HundredMBreaststrokeRepository hundredMBreaststrokeRepository;

    @Autowired
    private HundredMFreestyleRepository hundredMFreestyleRepository;

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

    @Transactional
    public String deleteAthlete(Integer athleteId){
        Athlete athlete = getAthlete(athleteId);
        if (athlete.getFiftyMBackstroke()) {
            fiftyMBackstrokeRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getFiftyMBreaststroke()) {
            fiftyMBreaststrokeRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getFiftyMButterflystroke()) {
            fiftyMButterflystrokeRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getFiftyMFreestyle()) {
            fiftyMFreeStyleRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getFiftyMKickBoard()) {
            fiftyMKickBoardRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getHundredMBreaststroke()) {
            hundredMBreaststrokeRepository.deleteByAthleteId(athlete);
        }
        if (athlete.getHundredMFreestyle()) {
            hundredMFreestyleRepository.deleteByAthleteId(athlete);
        }
        athleteRepository.deleteById(athleteId);
        return "Athlete removed";
    }

    @Transactional
    public String deleteAllAthlete(List<Integer> athleteIds) {
        athleteRepository.deleteAllById(athleteIds);
        return "Athlete removed";
    }
}
