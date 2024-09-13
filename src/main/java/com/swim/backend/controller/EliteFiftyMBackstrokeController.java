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

import com.swim.backend.dto.EliteFiftyMBackstrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBackstroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteFiftyMBackstrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteFiftyMBackstroke")
public class EliteFiftyMBackstrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteFiftyMBackstrokeService eliteFiftyMBackstrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteFiftyMBackstroke")
    public List<EliteFiftyMBackstroke> listEliteFiftyMBackstroke() {
        Log4j2.entryLog("", className, "listEliteFiftyMBackstroke");
        List<EliteFiftyMBackstroke> eliteFiftyMBackstrokeList = eliteFiftyMBackstrokeService.listEliteFiftyMBackstroke();
        Log4j2.exitLog("", className, "listEliteFiftyMBackstroke eliteFiftyMBackstrokeList = " + eliteFiftyMBackstrokeList);
        return eliteFiftyMBackstrokeList;
    }

    @GetMapping("/listEliteFiftyMBackstrokeByGenderAndAge")
    public List<EliteFiftyMBackstroke> listEliteFiftyMBackstrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listEliteFiftyMBackstrokeByGenderAndAge");

            List<EliteFiftyMBackstroke> eliteFiftyMBackstrokeList = new ArrayList<>();

            if(age == 15) {
                eliteFiftyMBackstrokeList = eliteFiftyMBackstrokeService.listEliteFiftyMBackstroke().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                eliteFiftyMBackstrokeList = eliteFiftyMBackstrokeService.listEliteFiftyMBackstrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listEliteFiftyMBackstroke eliteFiftyMBackstrokeList = " + eliteFiftyMBackstrokeList);

        return eliteFiftyMBackstrokeList;
    }

    @GetMapping("/getEliteFiftyMBackstroke")
    public EliteFiftyMBackstrokeDto getEliteFiftyMBackstrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteFiftyMBackstrokeById id = " + id);

        EliteFiftyMBackstroke dbEliteFiftyMBackstroke = eliteFiftyMBackstrokeService.geteliteFiftyMBackstroke(id);
        EliteFiftyMBackstrokeDto eliteFiftyMBackstrokeDto = new EliteFiftyMBackstrokeDto(dbEliteFiftyMBackstroke);

        Log4j2.exitLog("", className, "getEliteFiftyMBackstrokeById eliteFiftyMBackstrokeDto = " + eliteFiftyMBackstrokeDto);

        return eliteFiftyMBackstrokeDto;
    }

    @PostMapping("/addEliteFiftyMBackstroke")
    public ResponseEntity<EliteFiftyMBackstroke> addEliteFiftyMBackstroke(@RequestBody EliteFiftyMBackstrokeDto newEliteFiftyMBackstroke) {

        Log4j2.entryLog("", className, "addEliteFiftyMBackstroke newEliteFiftyMBackstroke = " + newEliteFiftyMBackstroke);
        try {

            EliteFiftyMBackstroke eliteFiftyMBackstroke = newEliteFiftyMBackstroke.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteFiftyMBackstroke.getAthleteId().getAthleteId());
            eliteFiftyMBackstroke.setAthleteId(athlete);
            eliteFiftyMBackstroke = eliteFiftyMBackstrokeService.saveEliteFiftyMBackstroke(eliteFiftyMBackstroke); 
            
            Log4j2.exitLog("", className, "addEliteFiftyMBackstroke eliteFiftyMBackstroke = " + eliteFiftyMBackstroke);
            
            return new ResponseEntity<>(eliteFiftyMBackstroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteFiftyMBackstroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteFiftyMBackstroke")
    public ResponseEntity<EliteFiftyMBackstroke> updateEliteFiftyMBackstroke(@RequestBody EliteFiftyMBackstrokeDto newEliteFiftyMBackstroke) {

        Log4j2.entryLog("", className, "updateEliteFiftyMBackstroke newEliteFiftyMBackstroke = " + newEliteFiftyMBackstroke);

        try {
            EliteFiftyMBackstroke dbEliteFiftyMBackstroke = eliteFiftyMBackstrokeService.geteliteFiftyMBackstroke(newEliteFiftyMBackstroke.getId());

            dbEliteFiftyMBackstroke.setTime(newEliteFiftyMBackstroke.getTime());
            EliteFiftyMBackstroke eliteFiftyMBackstroke = eliteFiftyMBackstrokeService.saveOrUpdateEliteFiftyMBackstroke(dbEliteFiftyMBackstroke);

            Log4j2.exitLog("", className, "updateEliteFiftyMBackstroke eliteFiftyMBackstroke = " + eliteFiftyMBackstroke);

            return new ResponseEntity<>(eliteFiftyMBackstroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteFiftyMBackstroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteFiftyMBackstroke")
    public ResponseEntity<HttpStatus> deleteEliteFiftyMBackstroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteFiftyMBackstroke id = " + id);

        try {
            eliteFiftyMBackstrokeService.deleteEliteFiftyMBackstroke(id);
            Log4j2.exitLog("", className, "deleteEliteFiftyMBackstroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
