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

import com.swim.backend.dto.EliteFiftyMFreeStyleDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMFreeStyle;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.EliteFiftyMFreeStyleService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/eliteFiftyMFreeStyle")
public class EliteFiftyMFreeStyleController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private EliteFiftyMFreeStyleService eliteFiftyMFreeStyleService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listEliteFiftyMFreeStyle")
    public List<EliteFiftyMFreeStyle> listEliteFiftyMFreeStyle() {
        Log4j2.entryLog("", className, "listEliteFiftyMFreeStyle");
        List<EliteFiftyMFreeStyle> eliteFiftyMFreeStyleList = eliteFiftyMFreeStyleService.listEliteFiftyMFreeStyle();
        Log4j2.exitLog("", className, "listEliteFiftyMFreeStyle eliteFiftyMFreeStyleList = " + eliteFiftyMFreeStyleList);
        return eliteFiftyMFreeStyleList;
    }

    @GetMapping("/getEliteFiftyMFreeStyle")
    public EliteFiftyMFreeStyleDto getEliteFiftyMFreeStyleById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getEliteFiftyMFreeStyleById id = " + id);

        EliteFiftyMFreeStyle dbEliteFiftyMFreeStyle = eliteFiftyMFreeStyleService.getEliteFiftyMFreeStyle(id);
        EliteFiftyMFreeStyleDto eliteFiftyMFreeStyleDto = new EliteFiftyMFreeStyleDto(dbEliteFiftyMFreeStyle);

        Log4j2.exitLog("", className, "getEliteFiftyMFreeStyleById eliteFiftyMFreeStyleDto = " + eliteFiftyMFreeStyleDto);

        return eliteFiftyMFreeStyleDto;
    }

    @PostMapping("/addEliteFiftyMFreeStyle")
    public ResponseEntity<EliteFiftyMFreeStyle> addEliteFiftyMFreeStyle(@RequestBody EliteFiftyMFreeStyleDto newEliteFiftyMFreeStyle) {

        Log4j2.entryLog("", className, "addEliteFiftyMFreeStyle newEliteFiftyMFreeStyle = " + newEliteFiftyMFreeStyle);
        try {

            EliteFiftyMFreeStyle eliteFiftyMFreeStyle = newEliteFiftyMFreeStyle.toModel();
            Athlete athlete = athleteService.getAthlete(newEliteFiftyMFreeStyle.getAthleteId().getAthleteId());
            eliteFiftyMFreeStyle.setAthleteId(athlete);
            eliteFiftyMFreeStyle = eliteFiftyMFreeStyleService.saveEliteFiftyMFreeStyle(eliteFiftyMFreeStyle); 
            
            Log4j2.exitLog("", className, "addEliteFiftyMFreeStyle eliteFiftyMFreeStyle = " + eliteFiftyMFreeStyle);
            
            return new ResponseEntity<>(eliteFiftyMFreeStyle, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addEliteFiftyMFreeStyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEliteFiftyMFreeStyle")
    public ResponseEntity<EliteFiftyMFreeStyle> updateEliteFiftyMFreeStyle(@RequestBody EliteFiftyMFreeStyleDto newEliteFiftyMFreeStyle) {

        Log4j2.entryLog("", className, "updateEliteFiftyMFreeStyle newEliteFiftyMFreeStyle = " + newEliteFiftyMFreeStyle);

        try {
            EliteFiftyMFreeStyle dbEliteFiftyMFreeStyle = eliteFiftyMFreeStyleService.getEliteFiftyMFreeStyle(newEliteFiftyMFreeStyle.getId());

            dbEliteFiftyMFreeStyle.setTime(newEliteFiftyMFreeStyle.getTime());
            EliteFiftyMFreeStyle eliteFiftyMFreeStyle = eliteFiftyMFreeStyleService.saveOrUpdateEliteFiftyMFreeStyle(dbEliteFiftyMFreeStyle);

            Log4j2.exitLog("", className, "updateEliteFiftyMFreeStyle eliteFiftyMFreeStyle = " + eliteFiftyMFreeStyle);

            return new ResponseEntity<>(eliteFiftyMFreeStyle, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateEliteFiftyMFreeStyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEliteFiftyMFreeStyle")
    public ResponseEntity<HttpStatus> deleteEliteFiftyMFreeStyle(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteEliteFiftyMFreeStyle id = " + id);

        try {
            eliteFiftyMFreeStyleService.deleteEliteFiftyMFreeStyle(id);
            Log4j2.exitLog("", className, "deleteEliteFiftyMFreeStyle Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
