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

import com.swim.backend.dto.FiftyMBackstrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBackstroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMBackstrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/fiftyMBackstroke")
public class FiftyMBackstrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMBackstrokeService fiftyMBackstrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listFiftyMBackstroke")
    public List<FiftyMBackstroke> listFiftyMBackstroke() {
        Log4j2.entryLog("", className, "listFiftyMBackstroke");
        List<FiftyMBackstroke> fiftyMBackstrokeList = fiftyMBackstrokeService.listFiftyMBackstroke();
        Log4j2.exitLog("", className, "listFiftyMBackstroke fiftyMBackstrokeList = " + fiftyMBackstrokeList);
        return fiftyMBackstrokeList;
    }

    @GetMapping("/listFiftyMBackstrokeByGenderAndAge")
    public List<FiftyMBackstroke> listFiftyMBackstrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listFiftyMBackstrokeByGenderAndAge");

            List<FiftyMBackstroke> fiftyMBackstrokeList = new ArrayList<>();

            if(age == 15) {
                fiftyMBackstrokeList = fiftyMBackstrokeService.listFiftyMBackstroke().stream()
                    .filter(h -> h.getAge() >= 15)
                    .collect(Collectors.toList());
            } else {
                fiftyMBackstrokeList = fiftyMBackstrokeService.listFiftyMBackstrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listFiftyMBackstroke fiftyMBackstrokeList = " + fiftyMBackstrokeList);

        return fiftyMBackstrokeList;
    }

    @GetMapping("/getFiftyMBackstroke")
    public FiftyMBackstrokeDto getFiftyMBackstrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getFiftyMBackstrokeById id = " + id);

        FiftyMBackstroke dbFiftyMBackstroke = fiftyMBackstrokeService.getfiftyMBackstroke(id);
        FiftyMBackstrokeDto fiftyMBackstrokeDto = new FiftyMBackstrokeDto(dbFiftyMBackstroke);

        Log4j2.exitLog("", className, "getFiftyMBackstrokeById fiftyMBackstrokeDto = " + fiftyMBackstrokeDto);

        return fiftyMBackstrokeDto;
    }

    @PostMapping("/addFiftyMBackstroke")
    public ResponseEntity<FiftyMBackstroke> addFiftyMBackstroke(@RequestBody FiftyMBackstrokeDto newFiftyMBackstroke) {

        Log4j2.entryLog("", className, "addFiftyMBackstroke newFiftyMBackstroke = " + newFiftyMBackstroke);
        try {

            FiftyMBackstroke fiftyMBackstroke = newFiftyMBackstroke.toModel();
            Athlete athlete = athleteService.getAthlete(newFiftyMBackstroke.getAthleteId().getAthleteId());
            fiftyMBackstroke.setAthleteId(athlete);
            fiftyMBackstroke = fiftyMBackstrokeService.saveFiftyMBackstroke(fiftyMBackstroke); 
            
            Log4j2.exitLog("", className, "addFiftyMBackstroke fiftyMBackstroke = " + fiftyMBackstroke);
            
            return new ResponseEntity<>(fiftyMBackstroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addFiftyMBackstroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFiftyMBackstroke")
    public ResponseEntity<FiftyMBackstroke> updateFiftyMBackstroke(@RequestBody FiftyMBackstrokeDto newFiftyMBackstroke) {

        Log4j2.entryLog("", className, "updateFiftyMBackstroke newFiftyMBackstroke = " + newFiftyMBackstroke);

        try {
            FiftyMBackstroke dbFiftyMBackstroke = fiftyMBackstrokeService.getfiftyMBackstroke(newFiftyMBackstroke.getId());

            dbFiftyMBackstroke.setTime(newFiftyMBackstroke.getTime());
            FiftyMBackstroke fiftyMBackstroke = fiftyMBackstrokeService.saveOrUpdateFiftyMBackstroke(dbFiftyMBackstroke);

            Log4j2.exitLog("", className, "updateFiftyMBackstroke fiftyMBackstroke = " + fiftyMBackstroke);

            return new ResponseEntity<>(fiftyMBackstroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateFiftyMBackstroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFiftyMBackstroke")
    public ResponseEntity<HttpStatus> deleteFiftyMBackstroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteFiftyMBackstroke id = " + id);

        try {
            fiftyMBackstrokeService.deleteFiftyMBackstroke(id);
            Log4j2.exitLog("", className, "deleteFiftyMBackstroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
