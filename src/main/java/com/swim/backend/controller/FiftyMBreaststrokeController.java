package com.swim.backend.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.swim.backend.dto.FiftyMBreaststrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBreaststroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMBreaststrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/fiftyMBreaststroke")
public class FiftyMBreaststrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMBreaststrokeService fiftyMBreaststrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listFiftyMBreaststroke")
    public List<FiftyMBreaststroke> listFiftyMBreaststroke() {
        Log4j2.entryLog("", className, "listFiftyMBreaststroke");
        List<FiftyMBreaststroke> fiftyMBreaststrokeList = fiftyMBreaststrokeService.listFiftyMBreaststroke();
        Log4j2.exitLog("", className, "listFiftyMBreaststroke fiftyMBreaststrokeList = " + fiftyMBreaststrokeList);
        return fiftyMBreaststrokeList;
    }

    @GetMapping("/listFiftyMBreaststrokeByGenderAndAge")
    public List<FiftyMBreaststroke> listFiftyMBreaststrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listFiftyMBreaststrokeByGenderAndAge");

            List<FiftyMBreaststroke> fiftyMBreaststrokeList = new ArrayList<>();

            if(age == 15) {
                fiftyMBreaststrokeList = fiftyMBreaststrokeService.listFiftyMBreaststroke().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                fiftyMBreaststrokeList = fiftyMBreaststrokeService.listFiftyMBreaststrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listFiftyMBreaststroke fiftyMBreaststrokeList = " + fiftyMBreaststrokeList);

        return fiftyMBreaststrokeList;
    }

    @GetMapping("/getFiftyMBreaststroke")
    public FiftyMBreaststrokeDto getFiftyMBreaststrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getFiftyMBreaststrokeById id = " + id);

        FiftyMBreaststroke dbFiftyMBreaststroke = fiftyMBreaststrokeService.getFiftyMBreaststroke(id);
        FiftyMBreaststrokeDto fiftyMBreaststrokeDto = new FiftyMBreaststrokeDto(dbFiftyMBreaststroke);

        Log4j2.exitLog("", className, "getFiftyMBreaststrokeById fiftyMBreaststrokeDto = " + fiftyMBreaststrokeDto);

        return fiftyMBreaststrokeDto;
    }

    @PostMapping("/addFiftyMBreaststroke")
    public ResponseEntity<FiftyMBreaststroke> addFiftyMBreaststroke(@RequestBody FiftyMBreaststrokeDto newFiftyMBreaststroke) {

        Log4j2.entryLog("", className, "addFiftyMBreaststroke newFiftyMBreaststroke = " + newFiftyMBreaststroke);
        try {

            FiftyMBreaststroke fiftyMBreaststroke = newFiftyMBreaststroke.toModel();
            Athlete athlete = athleteService.getAthlete(newFiftyMBreaststroke.getAthleteId().getAthleteId());
            fiftyMBreaststroke.setAthleteId(athlete);
            fiftyMBreaststroke = fiftyMBreaststrokeService.saveFiftyMBreaststroke(fiftyMBreaststroke); 
            
            Log4j2.exitLog("", className, "addFiftyMBreaststroke fiftyMBreaststroke = " + fiftyMBreaststroke);
            
            return new ResponseEntity<>(fiftyMBreaststroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addFiftyMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFiftyMBreaststroke")
    public ResponseEntity<FiftyMBreaststroke> updateFiftyMBreaststroke(@RequestBody FiftyMBreaststrokeDto newFiftyMBreaststroke) {

        Log4j2.entryLog("", className, "updateFiftyMBreaststroke newFiftyMBreaststroke = " + newFiftyMBreaststroke);

        try {
            FiftyMBreaststroke dbFiftyMBreaststroke = fiftyMBreaststrokeService.getFiftyMBreaststroke(newFiftyMBreaststroke.getId());

            dbFiftyMBreaststroke.setTime(newFiftyMBreaststroke.getTime());
            FiftyMBreaststroke fiftyMBreaststroke = fiftyMBreaststrokeService.saveOrUpdateFiftyMBreaststroke(dbFiftyMBreaststroke);

            Log4j2.exitLog("", className, "updateFiftyMBreaststroke fiftyMBreaststroke = " + fiftyMBreaststroke);

            return new ResponseEntity<>(fiftyMBreaststroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateFiftyMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFiftyMBreaststroke")
    public ResponseEntity<HttpStatus> deleteFiftyMBreaststroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteFiftyMBreaststroke id = " + id);

        try {
            fiftyMBreaststrokeService.deleteFiftyMBreaststroke(id);
            Log4j2.exitLog("", className, "deleteFiftyMBreaststroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
