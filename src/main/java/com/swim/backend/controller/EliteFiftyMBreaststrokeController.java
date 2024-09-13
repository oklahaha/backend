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

import com.swim.backend.dto.EliteFiftyMBreaststrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBreaststroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteFiftyMBreaststrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteFiftyMBreaststroke")
public class EliteFiftyMBreaststrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteFiftyMBreaststrokeService eliteFiftyMBreaststrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteFiftyMBreaststroke")
    public List<EliteFiftyMBreaststroke> listEliteFiftyMBreaststroke() {
        Log4j2.entryLog("", className, "listEliteFiftyMBreaststroke");
        List<EliteFiftyMBreaststroke> eliteFiftyMBreaststrokeList = eliteFiftyMBreaststrokeService.listEliteFiftyMBreaststroke();
        Log4j2.exitLog("", className, "listEliteFiftyMBreaststroke eliteFiftyMBreaststrokeList = " + eliteFiftyMBreaststrokeList);
        return eliteFiftyMBreaststrokeList;
    }

    @GetMapping("/listEliteFiftyMBreaststrokeByGenderAndAge")
    public List<EliteFiftyMBreaststroke> listEliteFiftyMBreaststrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listEliteFiftyMBreaststrokeByGenderAndAge");

            List<EliteFiftyMBreaststroke> eliteFiftyMBreaststrokeList = new ArrayList<>();

            if(age == 15) {
                eliteFiftyMBreaststrokeList = eliteFiftyMBreaststrokeService.listEliteFiftyMBreaststroke().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                eliteFiftyMBreaststrokeList = eliteFiftyMBreaststrokeService.listEliteFiftyMBreaststrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listEliteFiftyMBreaststroke eliteFiftyMBreaststrokeList = " + eliteFiftyMBreaststrokeList);

        return eliteFiftyMBreaststrokeList;
    }

    @GetMapping("/getEliteFiftyMBreaststroke")
    public EliteFiftyMBreaststrokeDto getEliteFiftyMBreaststrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteFiftyMBreaststrokeById id = " + id);

        EliteFiftyMBreaststroke dbEliteFiftyMBreaststroke = eliteFiftyMBreaststrokeService.getEliteFiftyMBreaststroke(id);
        EliteFiftyMBreaststrokeDto eliteFiftyMBreaststrokeDto = new EliteFiftyMBreaststrokeDto(dbEliteFiftyMBreaststroke);

        Log4j2.exitLog("", className, "getEliteFiftyMBreaststrokeById eliteFiftyMBreaststrokeDto = " + eliteFiftyMBreaststrokeDto);

        return eliteFiftyMBreaststrokeDto;
    }

    @PostMapping("/addEliteFiftyMBreaststroke")
    public ResponseEntity<EliteFiftyMBreaststroke> addEliteFiftyMBreaststroke(@RequestBody EliteFiftyMBreaststrokeDto newEliteFiftyMBreaststroke) {

        Log4j2.entryLog("", className, "addEliteFiftyMBreaststroke newEliteFiftyMBreaststroke = " + newEliteFiftyMBreaststroke);
        try {

            EliteFiftyMBreaststroke eliteFiftyMBreaststroke = newEliteFiftyMBreaststroke.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteFiftyMBreaststroke.getAthleteId().getAthleteId());
            eliteFiftyMBreaststroke.setAthleteId(athlete);
            eliteFiftyMBreaststroke = eliteFiftyMBreaststrokeService.saveEliteFiftyMBreaststroke(eliteFiftyMBreaststroke); 
            
            Log4j2.exitLog("", className, "addEliteFiftyMBreaststroke eliteFiftyMBreaststroke = " + eliteFiftyMBreaststroke);
            
            return new ResponseEntity<>(eliteFiftyMBreaststroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteFiftyMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteFiftyMBreaststroke")
    public ResponseEntity<EliteFiftyMBreaststroke> updateEliteFiftyMBreaststroke(@RequestBody EliteFiftyMBreaststrokeDto newEliteFiftyMBreaststroke) {

        Log4j2.entryLog("", className, "updateEliteFiftyMBreaststroke newEliteFiftyMBreaststroke = " + newEliteFiftyMBreaststroke);

        try {
            EliteFiftyMBreaststroke dbEliteFiftyMBreaststroke = eliteFiftyMBreaststrokeService.getEliteFiftyMBreaststroke(newEliteFiftyMBreaststroke.getId());

            dbEliteFiftyMBreaststroke.setTime(newEliteFiftyMBreaststroke.getTime());
            EliteFiftyMBreaststroke eliteFiftyMBreaststroke = eliteFiftyMBreaststrokeService.saveOrUpdateEliteFiftyMBreaststroke(dbEliteFiftyMBreaststroke);

            Log4j2.exitLog("", className, "updateEliteFiftyMBreaststroke eliteFiftyMBreaststroke = " + eliteFiftyMBreaststroke);

            return new ResponseEntity<>(eliteFiftyMBreaststroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteFiftyMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteFiftyMBreaststroke")
    public ResponseEntity<HttpStatus> deleteEliteFiftyMBreaststroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteFiftyMBreaststroke id = " + id);

        try {
            eliteFiftyMBreaststrokeService.deleteEliteFiftyMBreaststroke(id);
            Log4j2.exitLog("", className, "deleteEliteFiftyMBreaststroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
