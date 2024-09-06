package com.swim.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swim.backend.dto.AthleteDto;
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

    @GetMapping("/listAthlete")
    public List<Athlete> listAthlete() {
        return athleteService.listAthlete();
    }

    @GetMapping("/getAthlete")
    public Athlete getAthleteById(@RequestParam(name = "athleteId", required = true) Integer athleteId) {
        return athleteService.getAthlete(athleteId);
    }

    @PostMapping("/addAthlete")
    public ResponseEntity<Athlete> addAthlete(@RequestBody AthleteDto newAthlete) {
        try {
            Athlete athlete = newAthlete.toModel();
            athlete = athleteService.saveAthlete(athlete);
            return new ResponseEntity<>(athlete, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editAthlete")
    public ResponseEntity<Athlete> updateAthlete(@RequestBody AthleteDto newAthlete) {
        Optional<Athlete> dbAthlete = athleteRepository.findById(newAthlete.getAthleteId());

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
            if (newAthlete.getGender() != null) {
                updateAthlete.setGender(newAthlete.getGender());
            }
            if (newAthlete.getPhone() != null) {
                updateAthlete.setPhone(newAthlete.getPhone());
            }
            return new ResponseEntity<>(athleteService.saveOrUpdateAthlete(updateAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAthlete")
    public ResponseEntity<HttpStatus> deleteAthlete(@RequestParam(name = "athleteId", required = true) Integer athleteId) {
        try {
            athleteService.deleteAthlete(athleteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
