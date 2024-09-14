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

import com.swim.backend.dto.EliteFiftyMButterflystrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMButterflystroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteFiftyMButterflystrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteFiftyMButterflystroke")
public class EliteFiftyMButterflystrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteFiftyMButterflystrokeService eliteFiftyMButterflystrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteFiftyMButterflystroke")
    public List<EliteFiftyMButterflystroke> listEliteFiftyMButterflystroke() {
        Log4j2.entryLog("", className, "listEliteFiftyMButterflystroke");
        List<EliteFiftyMButterflystroke> eliteFiftyMButterflystrokeList = eliteFiftyMButterflystrokeService.listEliteFiftyMButterflystroke();
        Log4j2.exitLog("", className, "listEliteFiftyMButterflystroke eliteFiftyMButterflystrokeList = " + eliteFiftyMButterflystrokeList);
        return eliteFiftyMButterflystrokeList;
    }

    @GetMapping("/getEliteFiftyMButterflystroke")
    public EliteFiftyMButterflystrokeDto getEliteFiftyMButterflystrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteFiftyMButterflystrokeById id = " + id);

        EliteFiftyMButterflystroke dbEliteFiftyMButterflystroke = eliteFiftyMButterflystrokeService.getEliteFiftyMButterflystroke(id);
        EliteFiftyMButterflystrokeDto eliteFiftyMButterflystrokeDto = new EliteFiftyMButterflystrokeDto(dbEliteFiftyMButterflystroke);

        Log4j2.exitLog("", className, "getEliteFiftyMButterflystrokeById eliteFiftyMButterflystrokeDto = " + eliteFiftyMButterflystrokeDto);

        return eliteFiftyMButterflystrokeDto;
    }

    @PostMapping("/addEliteFiftyMButterflystroke")
    public ResponseEntity<EliteFiftyMButterflystroke> addEliteFiftyMButterflystroke(@RequestBody EliteFiftyMButterflystrokeDto newEliteFiftyMButterflystroke) {

        Log4j2.entryLog("", className, "addEliteFiftyMButterflystroke newEliteFiftyMButterflystroke = " + newEliteFiftyMButterflystroke);
        try {

            EliteFiftyMButterflystroke eliteFiftyMButterflystroke = newEliteFiftyMButterflystroke.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteFiftyMButterflystroke.getAthleteId().getAthleteId());
            eliteFiftyMButterflystroke.setAthleteId(athlete);
            eliteFiftyMButterflystroke = eliteFiftyMButterflystrokeService.saveEliteFiftyMButterflystroke(eliteFiftyMButterflystroke); 
            
            Log4j2.exitLog("", className, "addEliteFiftyMButterflystroke eliteFiftyMButterflystroke = " + eliteFiftyMButterflystroke);
            
            return new ResponseEntity<>(eliteFiftyMButterflystroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteFiftyMButterflystroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteFiftyMButterflystroke")
    public ResponseEntity<EliteFiftyMButterflystroke> updateEliteFiftyMButterflystroke(@RequestBody EliteFiftyMButterflystrokeDto newEliteFiftyMButterflystroke) {

        Log4j2.entryLog("", className, "updateEliteFiftyMButterflystroke newEliteFiftyMButterflystroke = " + newEliteFiftyMButterflystroke);

        try {
            EliteFiftyMButterflystroke dbEliteFiftyMButterflystroke = eliteFiftyMButterflystrokeService.getEliteFiftyMButterflystroke(newEliteFiftyMButterflystroke.getId());

            dbEliteFiftyMButterflystroke.setTime(newEliteFiftyMButterflystroke.getTime());
            EliteFiftyMButterflystroke eliteFiftyMButterflystroke = eliteFiftyMButterflystrokeService.saveOrUpdateEliteFiftyMButterflystroke(dbEliteFiftyMButterflystroke);

            Log4j2.exitLog("", className, "updateEliteFiftyMButterflystroke eliteFiftyMButterflystroke = " + eliteFiftyMButterflystroke);

            return new ResponseEntity<>(eliteFiftyMButterflystroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteFiftyMButterflystroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteFiftyMButterflystroke")
    public ResponseEntity<HttpStatus> deleteEliteFiftyMButterflystroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteFiftyMButterflystroke id = " + id);

        try {
            eliteFiftyMButterflystrokeService.deleteEliteFiftyMButterflystroke(id);
            Log4j2.exitLog("", className, "deleteEliteFiftyMButterflystroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
