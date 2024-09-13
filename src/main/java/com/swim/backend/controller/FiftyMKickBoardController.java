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

import com.swim.backend.dto.FiftyMKickBoardDto;
import com.swim.backend.enumeration.LogEventType;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMKickBoard;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMKickBoardService;
import com.swim.backend.utils.Log4j2;

@CrossOrigin
@RestController
@RequestMapping("/fiftyMKickBoard")
public class FiftyMKickBoardController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMKickBoardService fiftyMKickBoardService;

    String className = this.getClass().getSimpleName();

    @GetMapping("/listFiftyMKickBoard")
    public List<FiftyMKickBoard> listFiftyMKickBoard() {
        Log4j2.entryLog("", className, "listFiftyMKickBoard");
        List<FiftyMKickBoard> fiftyMKickBoardList = fiftyMKickBoardService.listFiftyMKickBoard();
        Log4j2.exitLog("", className, "listFiftyMKickBoard fiftyMKickBoardList = " + fiftyMKickBoardList);
        return fiftyMKickBoardList;
    }

    @GetMapping("/listFiftyMKickBoardByGenderAndAge")
    public List<FiftyMKickBoard> listFiftyMKickBoardByGenderAndAge(
        @RequestParam(name = "gender", required = true) String gender,
        @RequestParam(name = "age", required = true) Integer age
        ) {
            Log4j2.entryLog("", className, "listFiftyMKickBoardByGenderAndAge");

            List<FiftyMKickBoard> fiftyMKickBoardList = new ArrayList<>();

            if(age == 6) {
                fiftyMKickBoardList = fiftyMKickBoardService.listFiftyMKickBoard().stream()
                    .filter(h -> h.getAge() <= 6)
                    .collect(Collectors.toList());
            } else {
                fiftyMKickBoardList = fiftyMKickBoardService.listFiftyMKickBoardByGenderAndAge(gender, age);
            }

            Log4j2.exitLog("", className, "listFiftyMKickBoard fiftyMKickBoardList = " + fiftyMKickBoardList);

        return fiftyMKickBoardList;
    }

    @GetMapping("/getFiftyMKickBoard")
    public FiftyMKickBoardDto getFiftyMKickBoardById(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "getFiftyMKickBoardById id = " + id);

        FiftyMKickBoard dbFiftyMKickBoard = fiftyMKickBoardService.getFiftyMKickBoard(id);
        FiftyMKickBoardDto fiftyMKickBoardDto = new FiftyMKickBoardDto(dbFiftyMKickBoard);

        Log4j2.exitLog("", className, "getFiftyMKickBoardById fiftyMKickBoardDto = " + fiftyMKickBoardDto);

        return fiftyMKickBoardDto;
    }

    @PostMapping("/addFiftyMKickBoard")
    public ResponseEntity<FiftyMKickBoard> addFiftyMKickBoard(@RequestBody FiftyMKickBoardDto newFiftyMKickBoard) {

        Log4j2.entryLog("", className, "addFiftyMKickBoard newFiftyMKickBoard = " + newFiftyMKickBoard);
        try {

            FiftyMKickBoard fiftyMKickBoard = newFiftyMKickBoard.toModel();
            Athlete athlete = athleteService.getAthlete(newFiftyMKickBoard.getAthleteId().getAthleteId());
            fiftyMKickBoard.setAthleteId(athlete);
            fiftyMKickBoard = fiftyMKickBoardService.saveFiftyMKickBoard(fiftyMKickBoard); 
            
            Log4j2.exitLog("", className, "addFiftyMKickBoard fiftyMKickBoard = " + fiftyMKickBoard);
            
            return new ResponseEntity<>(fiftyMKickBoard, HttpStatus.CREATED);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "addFiftyMKickBoard e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFiftyMKickBoard")
    public ResponseEntity<FiftyMKickBoard> updateFiftyMKickBoard(@RequestBody FiftyMKickBoardDto newFiftyMKickBoard) {

        Log4j2.entryLog("", className, "updateFiftyMKickBoard newFiftyMKickBoard = " + newFiftyMKickBoard);

        try {
            FiftyMKickBoard dbFiftyMKickBoard = fiftyMKickBoardService.getFiftyMKickBoard(newFiftyMKickBoard.getId());

            dbFiftyMKickBoard.setTime(newFiftyMKickBoard.getTime());
            FiftyMKickBoard fiftyMKickBoard = fiftyMKickBoardService.saveOrUpdateFiftyMKickBoard(dbFiftyMKickBoard);

            Log4j2.exitLog("", className, "updateFiftyMKickBoard fiftyMKickBoard = " + fiftyMKickBoard);

            return new ResponseEntity<>(fiftyMKickBoard, HttpStatus.OK);
        } catch (Exception e) {
            Log4j2.logSystemError(LogEventType.ERROR, "", className, "updateFiftyMKickBoard e = " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFiftyMKickBoard")
    public ResponseEntity<HttpStatus> deleteFiftyMKickBoard(@RequestParam(name = "id", required = true) String id) {
        Log4j2.entryLog("", className, "deleteFiftyMKickBoard id = " + id);

        try {
            fiftyMKickBoardService.deleteFiftyMKickBoard(id);
            Log4j2.exitLog("", className, "deleteFiftyMKickBoard Success");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
