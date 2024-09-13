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

import com.swim.backend.dto.HundredMBreaststrokeDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMBreaststroke;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.HundredMBreaststrokeService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/hundredMBreaststroke")
public class HundredMBreaststrokeController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private HundredMBreaststrokeService hundredMBreaststrokeService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listHundredMBreaststroke")
    public List<HundredMBreaststroke> listHundredMBreaststroke() {
        Log4j2.entryLog("", className, "listHundredMBreaststroke");
        List<HundredMBreaststroke> hundredMBreaststrokeList = hundredMBreaststrokeService.listHundredMBreaststroke();
        Log4j2.exitLog("", className, "listHundredMBreaststroke hundredMBreaststrokeList = " + hundredMBreaststrokeList);
        return hundredMBreaststrokeList;
    }

    @GetMapping("/listHundredMBreaststrokeByGenderAndAge")
    public List<HundredMBreaststroke> listHundredMBreaststrokeByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listHundredMBreaststrokeByGenderAndAge");

            List<HundredMBreaststroke> hundredMBreaststrokeList = new ArrayList<>();

            if(age == 15) {
                hundredMBreaststrokeList = hundredMBreaststrokeService.listHundredMBreaststroke().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                hundredMBreaststrokeList = hundredMBreaststrokeService.listHundredMBreaststrokeByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listHundredMBreaststroke hundredMBreaststrokeList = " + hundredMBreaststrokeList);

        return hundredMBreaststrokeList;
    }

    @GetMapping("/getHundredMBreaststroke")
    public HundredMBreaststrokeDto getHundredMBreaststrokeById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getHundredMBreaststrokeById id = " + id);

        HundredMBreaststroke dbHundredMBreaststroke = hundredMBreaststrokeService.getHundredMBreaststroke(id);
        HundredMBreaststrokeDto hundredMBreaststrokeDto = new HundredMBreaststrokeDto(dbHundredMBreaststroke);

        Log4j2.exitLog("", className, "getHundredMBreaststrokeById hundredMBreaststrokeDto = " + hundredMBreaststrokeDto);

        return hundredMBreaststrokeDto;
    }

    @PostMapping("/addHundredMBreaststroke")
    public ResponseEntity<HundredMBreaststroke> addHundredMBreaststroke(@RequestBody HundredMBreaststrokeDto newHundredMBreaststroke) {

        Log4j2.entryLog("", className, "addHundredMBreaststroke newHundredMBreaststroke = " + newHundredMBreaststroke);
        try {

            HundredMBreaststroke hundredMBreaststroke = newHundredMBreaststroke.toModel();
            Athlete athlete = athleteService.getAthlete(newHundredMBreaststroke.getAthleteId().getAthleteId());
            hundredMBreaststroke.setAthleteId(athlete);
            hundredMBreaststroke = hundredMBreaststrokeService.saveHundredMBreaststroke(hundredMBreaststroke); 
            
            Log4j2.exitLog("", className, "addHundredMBreaststroke hundredMBreaststroke = " + hundredMBreaststroke);
            
            return new ResponseEntity<>(hundredMBreaststroke, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addHundredMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editHundredMBreaststroke")
    public ResponseEntity<HundredMBreaststroke> updateHundredMBreaststroke(@RequestBody HundredMBreaststrokeDto newHundredMBreaststroke) {

        Log4j2.entryLog("", className, "updateHundredMBreaststroke newHundredMBreaststroke = " + newHundredMBreaststroke);

        try {
            HundredMBreaststroke dbHundredMBreaststroke = hundredMBreaststrokeService.getHundredMBreaststroke(newHundredMBreaststroke.getId());

            dbHundredMBreaststroke.setTime(newHundredMBreaststroke.getTime());
            HundredMBreaststroke hundredMBreaststroke = hundredMBreaststrokeService.saveOrUpdateHundredMBreaststroke(dbHundredMBreaststroke);

            Log4j2.exitLog("", className, "updateHundredMBreaststroke hundredMBreaststroke = " + hundredMBreaststroke);

            return new ResponseEntity<>(hundredMBreaststroke, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateHundredMBreaststroke e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteHundredMBreaststroke")
    public ResponseEntity<HttpStatus> deleteHundredMBreaststroke(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteHundredMBreaststroke id = " + id);

        try {
            hundredMBreaststrokeService.deleteHundredMBreaststroke(id);
            Log4j2.exitLog("", className, "deleteHundredMBreaststroke Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
