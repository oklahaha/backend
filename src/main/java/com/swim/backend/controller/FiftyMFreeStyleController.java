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

import com.swim.backend.dto.FiftyMFreeStyleDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMFreeStyle;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMFreeStyleService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/fiftyMFreeStyle")
public class FiftyMFreeStyleController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMFreeStyleService fiftyMFreeStyleService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listFiftyMFreeStyle")
    public List<FiftyMFreeStyle> listFiftyMFreeStyle() {
        Log4j2.entryLog("", className, "listFiftyMFreeStyle");
        List<FiftyMFreeStyle> fiftyMFreeStyleList = fiftyMFreeStyleService.listFiftyMFreeStyle();
        Log4j2.exitLog("", className, "listFiftyMFreeStyle fiftyMFreeStyleList = " + fiftyMFreeStyleList);
        return fiftyMFreeStyleList;
    }

    @GetMapping("/listFiftyMFreeStyleByGenderAndAge")
    public List<FiftyMFreeStyle> listFiftyMFreeStyleByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listFiftyMFreeStyleByGenderAndAge");

            List<FiftyMFreeStyle> fiftyMFreeStyleList = new ArrayList<>();

            if(age == 15) {
                fiftyMFreeStyleList = fiftyMFreeStyleService.listFiftyMFreeStyle().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                fiftyMFreeStyleList = fiftyMFreeStyleService.listFiftyMFreeStyleByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listFiftyMFreeStyle fiftyMFreeStyleList = " + fiftyMFreeStyleList);

        return fiftyMFreeStyleList;
    }

    @GetMapping("/getFiftyMFreeStyle")
    public FiftyMFreeStyleDto getFiftyMFreeStyleById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getFiftyMFreeStyleById id = " + id);

        FiftyMFreeStyle dbFiftyMFreeStyle = fiftyMFreeStyleService.getFiftyMFreeStyle(id);
        FiftyMFreeStyleDto fiftyMFreeStyleDto = new FiftyMFreeStyleDto(dbFiftyMFreeStyle);

        Log4j2.exitLog("", className, "getFiftyMFreeStyleById fiftyMFreeStyleDto = " + fiftyMFreeStyleDto);

        return fiftyMFreeStyleDto;
    }

    @PostMapping("/addFiftyMFreeStyle")
    public ResponseEntity<FiftyMFreeStyle> addFiftyMFreeStyle(@RequestBody FiftyMFreeStyleDto newFiftyMFreeStyle) {

        Log4j2.entryLog("", className, "addFiftyMFreeStyle newFiftyMFreeStyle = " + newFiftyMFreeStyle);
        try {

            FiftyMFreeStyle fiftyMFreeStyle = newFiftyMFreeStyle.toModel();
            Athlete athlete = athleteService.getAthlete(newFiftyMFreeStyle.getAthleteId().getAthleteId());
            fiftyMFreeStyle.setAthleteId(athlete);
            fiftyMFreeStyle = fiftyMFreeStyleService.saveFiftyMFreeStyle(fiftyMFreeStyle); 
            
            Log4j2.exitLog("", className, "addFiftyMFreeStyle fiftyMFreeStyle = " + fiftyMFreeStyle);
            
            return new ResponseEntity<>(fiftyMFreeStyle, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addFiftyMFreeStyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFiftyMFreeStyle")
    public ResponseEntity<FiftyMFreeStyle> updateFiftyMFreeStyle(@RequestBody FiftyMFreeStyleDto newFiftyMFreeStyle) {

        Log4j2.entryLog("", className, "updateFiftyMFreeStyle newFiftyMFreeStyle = " + newFiftyMFreeStyle);

        try {
            FiftyMFreeStyle dbFiftyMFreeStyle = fiftyMFreeStyleService.getFiftyMFreeStyle(newFiftyMFreeStyle.getId());

            dbFiftyMFreeStyle.setTime(newFiftyMFreeStyle.getTime());
            FiftyMFreeStyle fiftyMFreeStyle = fiftyMFreeStyleService.saveOrUpdateFiftyMFreeStyle(dbFiftyMFreeStyle);

            Log4j2.exitLog("", className, "updateFiftyMFreeStyle fiftyMFreeStyle = " + fiftyMFreeStyle);

            return new ResponseEntity<>(fiftyMFreeStyle, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateFiftyMFreeStyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFiftyMFreeStyle")
    public ResponseEntity<HttpStatus> deleteFiftyMFreeStyle(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteFiftyMFreeStyle id = " + id);

        try {
            fiftyMFreeStyleService.deleteFiftyMFreeStyle(id);
            Log4j2.exitLog("", className, "deleteFiftyMFreeStyle Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
