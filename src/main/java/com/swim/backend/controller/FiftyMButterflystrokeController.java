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

import com.swim.backend.dto.FiftyMButterflystrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMButterflystroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMButterflystrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/fiftyMButterflystroke")
public class FiftyMButterflystrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMButterflystrokeService fiftyMButterflystrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listFiftyMButterflystroke")
    public List<FiftyMButterflystroke> listFiftyMButterflystroke() {
        Log4j2.entryLog("", className, "listFiftyMButterflystroke");
        List<FiftyMButterflystroke> fiftyMButterflystrokeList = fiftyMButterflystrokeService.listFiftyMButterflystroke();
        Log4j2.exitLog("", className, "listFiftyMButterflystroke fiftyMButterflystrokeList = " + fiftyMButterflystrokeList);
        return fiftyMButterflystrokeList;
    }

    @GetMapping("/listFiftyMButterflystrokeByGenderAndAge")
    public List<FiftyMButterflystroke> listFiftyMButterflystrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listFiftyMButterflystrokeByGenderAndAge");

            List<FiftyMButterflystroke> fiftyMButterflystrokeList = new ArrayList<>();

            if(age == 15) {
                fiftyMButterflystrokeList = fiftyMButterflystrokeService.listFiftyMButterflystroke().stream()
                    .filter(h -> h.getAge() >= 15)
                    .collect(Collectors.toList());
            } else {
                fiftyMButterflystrokeList = fiftyMButterflystrokeService.listFiftyMButterflystrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listFiftyMButterflystroke fiftyMButterflystrokeList = " + fiftyMButterflystrokeList);

        return fiftyMButterflystrokeList;
    }

    @GetMapping("/getFiftyMButterflystroke")
    public FiftyMButterflystrokeDto getFiftyMButterflystrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getFiftyMButterflystrokeById id = " + id);

        FiftyMButterflystroke dbFiftyMButterflystroke = fiftyMButterflystrokeService.getFiftyMButterflystroke(id);
        FiftyMButterflystrokeDto fiftyMButterflystrokeDto = new FiftyMButterflystrokeDto(dbFiftyMButterflystroke);

        Log4j2.exitLog("", className, "getFiftyMButterflystrokeById fiftyMButterflystrokeDto = " + fiftyMButterflystrokeDto);

        return fiftyMButterflystrokeDto;
    }

    @PostMapping("/addFiftyMButterflystroke")
    public ResponseEntity<FiftyMButterflystroke> addFiftyMButterflystroke(@RequestBody FiftyMButterflystrokeDto newFiftyMButterflystroke) {

        Log4j2.entryLog("", className, "addFiftyMButterflystroke newFiftyMButterflystroke = " + newFiftyMButterflystroke);
        try {

            FiftyMButterflystroke fiftyMButterflystroke = newFiftyMButterflystroke.toModel();
            Athlete athlete = athleteService.getAthlete(newFiftyMButterflystroke.getAthleteId().getAthleteId());
            fiftyMButterflystroke.setAthleteId(athlete);
            fiftyMButterflystroke = fiftyMButterflystrokeService.saveFiftyMButterflystroke(fiftyMButterflystroke); 
            
            Log4j2.exitLog("", className, "addFiftyMButterflystroke fiftyMButterflystroke = " + fiftyMButterflystroke);
            
            return new ResponseEntity<>(fiftyMButterflystroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addFiftyMButterflystroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFiftyMButterflystroke")
    public ResponseEntity<FiftyMButterflystroke> updateFiftyMButterflystroke(@RequestBody FiftyMButterflystrokeDto newFiftyMButterflystroke) {

        Log4j2.entryLog("", className, "updateFiftyMButterflystroke newFiftyMButterflystroke = " + newFiftyMButterflystroke);

        try {
            FiftyMButterflystroke dbFiftyMButterflystroke = fiftyMButterflystrokeService.getFiftyMButterflystroke(newFiftyMButterflystroke.getId());

            dbFiftyMButterflystroke.setTime(newFiftyMButterflystroke.getTime());
            FiftyMButterflystroke fiftyMButterflystroke = fiftyMButterflystrokeService.saveOrUpdateFiftyMButterflystroke(dbFiftyMButterflystroke);

            Log4j2.exitLog("", className, "updateFiftyMButterflystroke fiftyMButterflystroke = " + fiftyMButterflystroke);

            return new ResponseEntity<>(fiftyMButterflystroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateFiftyMButterflystroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFiftyMButterflystroke")
    public ResponseEntity<HttpStatus> deleteFiftyMButterflystroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteFiftyMButterflystroke id = " + id);

        try {
            fiftyMButterflystrokeService.deleteFiftyMButterflystroke(id);
            Log4j2.exitLog("", className, "deleteFiftyMButterflystroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
