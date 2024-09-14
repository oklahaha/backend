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

import com.swim.backend.dto.EliteHundredMBreaststrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMBreaststroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteHundredMBreaststrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteHundredMBreaststroke")
public class EliteHundredMBreaststrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteHundredMBreaststrokeService eliteHundredMBreaststrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteHundredMBreaststroke")
    public List<EliteHundredMBreaststroke> listEliteHundredMBreaststroke() {
        Log4j2.entryLog("", className, "listEliteHundredMBreaststroke");
        List<EliteHundredMBreaststroke> eliteHundredMBreaststrokeList = eliteHundredMBreaststrokeService.listEliteHundredMBreaststroke();
        Log4j2.exitLog("", className, "listEliteHundredMBreaststroke eliteHundredMBreaststrokeList = " + eliteHundredMBreaststrokeList);
        return eliteHundredMBreaststrokeList;
    }

    @GetMapping("/getEliteHundredMBreaststroke")
    public EliteHundredMBreaststrokeDto getEliteHundredMBreaststrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteHundredMBreaststrokeById id = " + id);

        EliteHundredMBreaststroke dbEliteHundredMBreaststroke = eliteHundredMBreaststrokeService.getEliteHundredMBreaststroke(id);
        EliteHundredMBreaststrokeDto eliteHundredMBreaststrokeDto = new EliteHundredMBreaststrokeDto(dbEliteHundredMBreaststroke);

        Log4j2.exitLog("", className, "getEliteHundredMBreaststrokeById eliteHundredMBreaststrokeDto = " + eliteHundredMBreaststrokeDto);

        return eliteHundredMBreaststrokeDto;
    }

    @PostMapping("/addEliteHundredMBreaststroke")
    public ResponseEntity<EliteHundredMBreaststroke> addEliteHundredMBreaststroke(@RequestBody EliteHundredMBreaststrokeDto newEliteHundredMBreaststroke) {

        Log4j2.entryLog("", className, "addEliteHundredMBreaststroke newEliteHundredMBreaststroke = " + newEliteHundredMBreaststroke);
        try {

            EliteHundredMBreaststroke eliteHundredMBreaststroke = newEliteHundredMBreaststroke.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteHundredMBreaststroke.getAthleteId().getAthleteId());
            eliteHundredMBreaststroke.setAthleteId(athlete);
            eliteHundredMBreaststroke = eliteHundredMBreaststrokeService.saveEliteHundredMBreaststroke(eliteHundredMBreaststroke); 
            
            Log4j2.exitLog("", className, "addEliteHundredMBreaststroke eliteHundredMBreaststroke = " + eliteHundredMBreaststroke);
            
            return new ResponseEntity<>(eliteHundredMBreaststroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteHundredMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteHundredMBreaststroke")
    public ResponseEntity<EliteHundredMBreaststroke> updateEliteHundredMBreaststroke(@RequestBody EliteHundredMBreaststrokeDto newEliteHundredMBreaststroke) {

        Log4j2.entryLog("", className, "updateEliteHundredMBreaststroke newEliteHundredMBreaststroke = " + newEliteHundredMBreaststroke);

        try {
            EliteHundredMBreaststroke dbEliteHundredMBreaststroke = eliteHundredMBreaststrokeService.getEliteHundredMBreaststroke(newEliteHundredMBreaststroke.getId());

            dbEliteHundredMBreaststroke.setTime(newEliteHundredMBreaststroke.getTime());
            EliteHundredMBreaststroke eliteHundredMBreaststroke = eliteHundredMBreaststrokeService.saveOrUpdateEliteHundredMBreaststroke(dbEliteHundredMBreaststroke);

            Log4j2.exitLog("", className, "updateEliteHundredMBreaststroke eliteHundredMBreaststroke = " + eliteHundredMBreaststroke);

            return new ResponseEntity<>(eliteHundredMBreaststroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteHundredMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteHundredMBreaststroke")
    public ResponseEntity<HttpStatus> deleteEliteHundredMBreaststroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteHundredMBreaststroke id = " + id);

        try {
            eliteHundredMBreaststrokeService.deleteEliteHundredMBreaststroke(id);
            Log4j2.exitLog("", className, "deleteEliteHundredMBreaststroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
