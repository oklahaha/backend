package com.swim.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swim.backend.model.Athlete;
import com.swim.backend.repository.AthleteRepository;
import com.swim.backend.service.AthleteService;

@CrossOrigin
@RestController
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("/list")
    public List<Athlete> listAthlete() {
        return athleteService.listAthlete();
    }

    @GetMapping("/{athleteId}")
    public Athlete getAthleteById(@PathVariable Integer athleteId) {
        return athleteService.getAthlete(athleteId);
    }

    @PostMapping("/add")
    public ResponseEntity<Athlete> addAthlete(@RequestBody Athlete athlete) {
        try {
            Athlete newAthlete = athleteService.saveAthlete(athlete);
            return new ResponseEntity<>(newAthlete, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{athleteId}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable Integer athleteId, @RequestBody Athlete newAthlete) {
        Optional<Athlete> dbAthlete = athleteRepository.findById(athleteId);

        if (dbAthlete.isPresent()) {
            Athlete updateAthlete = dbAthlete.get();
            if (newAthlete.getEngName() != null) {
                updateAthlete.setEngName(newAthlete.getEngName());
            }
            if (newAthlete.getChName() != null) {
                updateAthlete.setChName(newAthlete.getChName());
            }
            if (newAthlete.getAge() != null) {
                updateAthlete.setAge(newAthlete.getAge());
            }
            if (newAthlete.getSex() != null) {
                updateAthlete.setSex(newAthlete.getSex());
            }
            if (newAthlete.getPhone() != null) {
                updateAthlete.setPhone(newAthlete.getPhone());
            }
            return new ResponseEntity<>(athleteService.saveOrUpdateAthlete(updateAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{athleteId}")
    public ResponseEntity<HttpStatus> deleteAthlete(@PathVariable Integer athleteId) {
        try {
            athleteService.deleteAthlete(athleteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
