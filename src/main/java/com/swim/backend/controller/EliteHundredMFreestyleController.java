package com.swim.backend.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

import com.swim.backend.dto.EliteHundredMFreestyleDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMFreestyle;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteHundredMFreestyleService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteHundredMFreestyle")
public class EliteHundredMFreestyleController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteHundredMFreestyleService eliteHundredMFreestyleService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteHundredMFreestyle")
    public List<EliteHundredMFreestyle> listEliteHundredMFreestyle() {
        Log4j2.entryLog("", className, "listEliteHundredMFreestyle");
        List<EliteHundredMFreestyle> eliteHundredMFreestyleList = eliteHundredMFreestyleService.listEliteHundredMFreestyle();
        Log4j2.exitLog("", className, "listEliteHundredMFreestyle eliteHundredMFreestyleList = " + eliteHundredMFreestyleList);
        return eliteHundredMFreestyleList;
    }

    @GetMapping("/listEliteHundredMFreestyleByGenderAndAge")
    public List<EliteHundredMFreestyle> listEliteHundredMFreestyleByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listEliteHundredMFreestyleByGenderAndAge");

            List<EliteHundredMFreestyle> eliteHundredMFreestyleList = new ArrayList<>();

            if(age == 15) {
                eliteHundredMFreestyleList = eliteHundredMFreestyleService.listEliteHundredMFreestyle().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                eliteHundredMFreestyleList = eliteHundredMFreestyleService.listEliteHundredMFreestyleByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listEliteHundredMFreestyle eliteHundredMFreestyleList = " + eliteHundredMFreestyleList);

        return eliteHundredMFreestyleList;
    }

    @GetMapping("/getEliteHundredMFreestyle")
    public EliteHundredMFreestyleDto getEliteHundredMFreestyleById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteHundredMFreestyleById id = " + id);

        EliteHundredMFreestyle dbEliteHundredMFreestyle = eliteHundredMFreestyleService.getEliteHundredMFreestyle(id);
        EliteHundredMFreestyleDto eliteHundredMFreestyleDto = new EliteHundredMFreestyleDto(dbEliteHundredMFreestyle);

        Log4j2.exitLog("", className, "getEliteHundredMFreestyleById eliteHundredMFreestyleDto = " + eliteHundredMFreestyleDto);

        return eliteHundredMFreestyleDto;
    }

    @PostMapping("/addEliteHundredMFreestyle")
    public ResponseEntity<EliteHundredMFreestyle> addEliteHundredMFreestyle(@RequestBody EliteHundredMFreestyleDto newEliteHundredMFreestyle) {

        Log4j2.entryLog("", className, "addEliteHundredMFreestyle newEliteHundredMFreestyle = " + newEliteHundredMFreestyle);
        try {

            EliteHundredMFreestyle eliteHundredMFreestyle = newEliteHundredMFreestyle.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteHundredMFreestyle.getAthleteId().getAthleteId());
            eliteHundredMFreestyle.setAthleteId(athlete);
            eliteHundredMFreestyle = eliteHundredMFreestyleService.saveEliteHundredMFreestyle(eliteHundredMFreestyle); 
            
            Log4j2.exitLog("", className, "addEliteHundredMFreestyle eliteHundredMFreestyle = " + eliteHundredMFreestyle);
            
            return new ResponseEntity<>(eliteHundredMFreestyle, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteHundredMFreestyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteHundredMFreestyle")
    public ResponseEntity<EliteHundredMFreestyle> updateEliteHundredMFreestyle(@RequestBody EliteHundredMFreestyleDto newEliteHundredMFreestyle) {

        Log4j2.entryLog("", className, "updateEliteHundredMFreestyle newEliteHundredMFreestyle = " + newEliteHundredMFreestyle);

        try {
            EliteHundredMFreestyle dbEliteHundredMFreestyle = eliteHundredMFreestyleService.getEliteHundredMFreestyle(newEliteHundredMFreestyle.getId());

            dbEliteHundredMFreestyle.setTime(newEliteHundredMFreestyle.getTime());
            EliteHundredMFreestyle eliteHundredMFreestyle = eliteHundredMFreestyleService.saveOrUpdateEliteHundredMFreestyle(dbEliteHundredMFreestyle);

            Log4j2.exitLog("", className, "updateEliteHundredMFreestyle eliteHundredMFreestyle = " + eliteHundredMFreestyle);

            return new ResponseEntity<>(eliteHundredMFreestyle, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteHundredMFreestyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteHundredMFreestyle")
    public ResponseEntity<HttpStatus> deleteEliteHundredMFreestyle(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteHundredMFreestyle id = " + id);

        try {
            eliteHundredMFreestyleService.deleteEliteHundredMFreestyle(id);
            Log4j2.exitLog("", className, "deleteEliteHundredMFreestyle Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
