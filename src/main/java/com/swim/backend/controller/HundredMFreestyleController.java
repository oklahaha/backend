package com.swim.backend.controller;

import java.util.List;
import java.util.ArrayList;
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

import com.swim.backend.dto.HundredMFreestyleDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMFreestyle;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.HundredMFreestyleService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/hundredMFreestyle")
public class HundredMFreestyleController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private HundredMFreestyleService hundredMFreestyleService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listHundredMFreestyle")
    public List<HundredMFreestyle> listHundredMFreestyle() {
        Log4j2.entryLog("", className, "listHundredMFreestyle");
        List<HundredMFreestyle> hundredMFreestyleList = hundredMFreestyleService.listHundredMFreestyle();
        Log4j2.exitLog("", className, "listHundredMFreestyle hundredMFreestyleList = " + hundredMFreestyleList);
        return hundredMFreestyleList;
    }

    @GetMapping("/listHundredMFreestyleByGenderAndAge")
    public List<HundredMFreestyle> listHundredMFreestyleByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listHundredMFreestyleByGenderAndAge");

            List<HundredMFreestyle> hundredMFreestyleList = new ArrayList<>();

            if(age == 15) {
                hundredMFreestyleList = hundredMFreestyleService.listHundredMFreestyle().stream()
                    .filter(h -> h.getAge() >= 15 && h.getGender().equals(gender))
                    .collect(Collectors.toList());
            } else {
                hundredMFreestyleList = hundredMFreestyleService.listHundredMFreestyleByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listHundredMFreestyle hundredMFreestyleList = " + hundredMFreestyleList);

        return hundredMFreestyleList;
    }

    @GetMapping("/getHundredMFreestyle")
    public HundredMFreestyleDto getHundredMFreestyleById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getHundredMFreestyleById id = " + id);

        HundredMFreestyle dbHundredMFreestyle = hundredMFreestyleService.getHundredMFreestyle(id);
        HundredMFreestyleDto hundredMFreestyleDto = new HundredMFreestyleDto(dbHundredMFreestyle);

        Log4j2.exitLog("", className, "getHundredMFreestyleById hundredMFreestyleDto = " + hundredMFreestyleDto);

        return hundredMFreestyleDto;
    }

    @PostMapping("/addHundredMFreestyle")
    public ResponseEntity<HundredMFreestyle> addHundredMFreestyle(@RequestBody HundredMFreestyleDto newHundredMFreestyle) {

        Log4j2.entryLog("", className, "addHundredMFreestyle newHundredMFreestyle = " + newHundredMFreestyle);
        try {

            HundredMFreestyle hundredMFreestyle = newHundredMFreestyle.toModel();
            Athlete athlete = athleteService.getAthlete(newHundredMFreestyle.getAthleteId().getAthleteId());
            hundredMFreestyle.setAthleteId(athlete);
            hundredMFreestyle = hundredMFreestyleService.saveHundredMFreestyle(hundredMFreestyle); 
            
            Log4j2.exitLog("", className, "addHundredMFreestyle hundredMFreestyle = " + hundredMFreestyle);
            
            return new ResponseEntity<>(hundredMFreestyle, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addHundredMFreestyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editHundredMFreestyle")
    public ResponseEntity<HundredMFreestyle> updateHundredMFreestyle(@RequestBody HundredMFreestyleDto newHundredMFreestyle) {

        Log4j2.entryLog("", className, "updateHundredMFreestyle newHundredMFreestyle = " + newHundredMFreestyle);

        try {
            HundredMFreestyle dbHundredMFreestyle = hundredMFreestyleService.getHundredMFreestyle(newHundredMFreestyle.getId());

            dbHundredMFreestyle.setTime(newHundredMFreestyle.getTime());
            HundredMFreestyle hundredMFreestyle = hundredMFreestyleService.saveOrUpdateHundredMFreestyle(dbHundredMFreestyle);

            Log4j2.exitLog("", className, "updateHundredMFreestyle hundredMFreestyle = " + hundredMFreestyle);

            return new ResponseEntity<>(hundredMFreestyle, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateHundredMFreestyle e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteHundredMFreestyle")
    public ResponseEntity<HttpStatus> deleteHundredMFreestyle(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteHundredMFreestyle id = " + id);

        try {
            hundredMFreestyleService.deleteHundredMFreestyle(id);
            Log4j2.exitLog("", className, "deleteHundredMFreestyle Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
