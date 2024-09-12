package com.swim.backend.controller;

import java.util.List;
import java.util.Optional;

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

import com.swim.backend.dto.AthleteDto;
import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBackstroke;
import com.swim.backend.model.FiftyMBreaststroke;
import com.swim.backend.model.FiftyMButterflystroke;
import com.swim.backend.model.FiftyMFreeStyle;
import com.swim.backend.model.FiftyMKickBoard;
import com.swim.backend.model.HundredMBreaststroke;
import com.swim.backend.model.HundredMFreestyle;
import com.swim.backend.repository.AthleteRepository;
import com.swim.backend.service.AthleteService;
import com.swim.backend.service.FiftyMBackstrokeService;
import com.swim.backend.service.FiftyMBreaststrokeService;
import com.swim.backend.service.FiftyMButterflystrokeService;
import com.swim.backend.service.FiftyMFreeStyleService;
import com.swim.backend.service.FiftyMKickBoardService;
import com.swim.backend.service.HundredMBreaststrokeService;
import com.swim.backend.service.HundredMFreestyleService;

@CrossOrigin
@RestController
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private FiftyMBackstrokeService fiftyMBackstrokeService;

    @Autowired
    private FiftyMBreaststrokeService fiftyMBreaststrokeService;

    @Autowired
    private FiftyMButterflystrokeService fiftyMButterflystrokeService;

    @Autowired
    private FiftyMFreeStyleService fiftyMFreeStyleService;

    @Autowired
    private FiftyMKickBoardService fiftyMKickBoardService;

    @Autowired
    private HundredMBreaststrokeService hundredMBreaststrokeService;

    @Autowired
    private HundredMFreestyleService hundredMFreestyleService;

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("/listAthlete")
    public List<Athlete> listAthlete() {
        return athleteService.listAthlete();
    }

    @GetMapping("/getAthlete")
    public AthleteDto getAthleteById(@RequestParam(name = "athleteId", required = true) Integer athleteId) {
        Athlete dbAthlete = athleteService.getAthlete(athleteId);
        AthleteDto athleteDto = new AthleteDto(dbAthlete);
        return athleteDto;
    }

    @PostMapping("/addAthlete")
    public ResponseEntity<Athlete> addAthlete(@RequestBody AthleteDto newAthlete) {
        try {
            Athlete athlete = newAthlete.toModel();
            athlete = athleteService.saveAthlete(athlete);
    
            if (athlete.getFiftyMFreestyle()) {
                FiftyMFreeStyle fiftyMFreeStyle = new FiftyMFreeStyle();
                fiftyMFreeStyle.setAthleteId(athlete);
                fiftyMFreeStyle.setGender(athlete.getGender());
                fiftyMFreeStyle.setAge(athlete.getAge());
                fiftyMFreeStyle.setDistrict(athlete.getDistrict());
                fiftyMFreeStyleService.saveFiftyMFreeStyle(fiftyMFreeStyle);
            }
    
            if (athlete.getFiftyMBackstroke()) {
                FiftyMBackstroke fiftyMBackstroke = new FiftyMBackstroke();
                fiftyMBackstroke.setAthleteId(athlete);
                fiftyMBackstroke.setGender(athlete.getGender());
                fiftyMBackstroke.setAge(athlete.getAge());
                fiftyMBackstroke.setDistrict(athlete.getDistrict());
                fiftyMBackstrokeService.saveFiftyMBackstroke(fiftyMBackstroke);
            }
    
            if (athlete.getFiftyMBreaststroke()) {
                FiftyMBreaststroke fiftyMBreaststroke = new FiftyMBreaststroke();
                fiftyMBreaststroke.setAthleteId(athlete);
                fiftyMBreaststroke.setGender(athlete.getGender());
                fiftyMBreaststroke.setAge(athlete.getAge());
                fiftyMBreaststroke.setDistrict(athlete.getDistrict());
                fiftyMBreaststrokeService.saveFiftyMBreaststroke(fiftyMBreaststroke);
            }
    
            if (athlete.getFiftyMButterflystroke()) {
                FiftyMButterflystroke fiftyMButterflystroke = new FiftyMButterflystroke();
                fiftyMButterflystroke.setAthleteId(athlete);
                fiftyMButterflystroke.setGender(athlete.getGender());
                fiftyMButterflystroke.setAge(athlete.getAge());
                fiftyMButterflystroke.setDistrict(athlete.getDistrict());
                fiftyMButterflystrokeService.saveFiftyMButterflystroke(fiftyMButterflystroke);
            }
    
            if (athlete.getHundredMFreestyle()) {
                HundredMFreestyle hundredMFreestyle = new HundredMFreestyle();
                hundredMFreestyle.setAthleteId(athlete);
                hundredMFreestyle.setGender(athlete.getGender());
                hundredMFreestyle.setAge(athlete.getAge());
                hundredMFreestyle.setDistrict(athlete.getDistrict());
                hundredMFreestyleService.saveHundredMFreestyle(hundredMFreestyle);
            }
    
            if (athlete.getHundredMBreaststroke()) {
                HundredMBreaststroke hundredMBreaststroke = new HundredMBreaststroke();
                hundredMBreaststroke.setAthleteId(athlete);
                hundredMBreaststroke.setGender(athlete.getGender());
                hundredMBreaststroke.setAge(athlete.getAge());
                hundredMBreaststroke.setDistrict(athlete.getDistrict());
                hundredMBreaststrokeService.saveHundredMBreaststroke(hundredMBreaststroke);
            }
    
            if (athlete.getFiftyMKickBoard()) {
                FiftyMKickBoard fiftyMKickBoard = new FiftyMKickBoard();
                fiftyMKickBoard.setAthleteId(athlete);
                fiftyMKickBoard.setGender(athlete.getGender());
                fiftyMKickBoard.setAge(athlete.getAge());
                fiftyMKickBoard.setDistrict(athlete.getDistrict());
                fiftyMKickBoardService.saveFiftyMKickBoard(fiftyMKickBoard);
            }
    
            return new ResponseEntity<>(athlete, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editAthlete")
    public ResponseEntity<Athlete> updateAthlete(@RequestBody AthleteDto newAthlete) {
        Optional<Athlete> dbAthlete = athleteRepository.findById(newAthlete.getAthleteId());

        if (dbAthlete.isPresent()) {
            Athlete updateAthlete = dbAthlete.get();

            // Delete All record of competition
            if (updateAthlete.getFiftyMBackstroke()) {
                fiftyMBackstrokeService.deleteFiftyMBackstrokeByAthlete(updateAthlete);
            }
            if (updateAthlete.getFiftyMBreaststroke()) {
                fiftyMBreaststrokeService.deleteFiftyMBreaststrokeByAthlete(updateAthlete);
            }
            if (updateAthlete.getFiftyMButterflystroke()) {
                fiftyMButterflystrokeService.deleteFiftyMButterflystrokeByAthlete(updateAthlete);
            }
            if (updateAthlete.getFiftyMFreestyle()) {
                fiftyMFreeStyleService.deleteFiftyMFreeStyleByAthlete(updateAthlete);
            }
            if (updateAthlete.getFiftyMKickBoard()) {
                fiftyMKickBoardService.deleteFiftyMKickBoardByAthlete(updateAthlete);
            }
            if (updateAthlete.getHundredMBreaststroke()) {
                hundredMBreaststrokeService.deleteHundredMBreaststrokeByAthlete(updateAthlete);
            }
            if (updateAthlete.getHundredMFreestyle()) {
                hundredMFreestyleService.deleteHundredMFreestyleByAthlete(updateAthlete);
            }

            // Update record
            if (newAthlete.getEngName() != null) {
                updateAthlete.setEngName(newAthlete.getEngName());
            }
            if (newAthlete.getChName() != null) {
                updateAthlete.setChName(newAthlete.getChName());
            }
            if (newAthlete.getAge() != null) {
                updateAthlete.setAge(newAthlete.getAge());
            }
            if (newAthlete.getGender() != null) {
                updateAthlete.setGender(newAthlete.getGender());
            }
            if (newAthlete.getDistrict() != null) {
                updateAthlete.setDistrict(newAthlete.getDistrict());
            }
            if (newAthlete.getPhone() != null) {
                updateAthlete.setPhone(newAthlete.getPhone());
            }
            if (newAthlete.getFiftyMBackstroke() != null) {
                updateAthlete.setFiftyMBackstroke(newAthlete.getFiftyMBackstroke());
            }
            if (newAthlete.getFiftyMBreaststroke() != null) {
                updateAthlete.setFiftyMBreaststroke(newAthlete.getFiftyMBreaststroke());
            }
            if (newAthlete.getFiftyMButterflystroke() != null) {
                updateAthlete.setFiftyMButterflystroke(newAthlete.getFiftyMButterflystroke());
            }
            if (newAthlete.getFiftyMFreestyle() != null) {
                updateAthlete.setFiftyMFreestyle(newAthlete.getFiftyMFreestyle());
            }
            if (newAthlete.getFiftyMKickBoard() != null) {
                updateAthlete.setFiftyMKickBoard(newAthlete.getFiftyMKickBoard());
            }
            if (newAthlete.getHundredMBreaststroke() != null) {
                updateAthlete.setHundredMBreaststroke(newAthlete.getHundredMBreaststroke());
            }
            if (newAthlete.getHundredMFreestyle() != null) {
                updateAthlete.setHundredMFreestyle(newAthlete.getHundredMFreestyle());
            }

            Athlete athlete = athleteService.saveOrUpdateAthlete(updateAthlete);

            if (athlete.getFiftyMFreestyle()) {
                FiftyMFreeStyle fiftyMFreeStyle = new FiftyMFreeStyle();
                fiftyMFreeStyle.setAthleteId(athlete);
                fiftyMFreeStyle.setGender(athlete.getGender());
                fiftyMFreeStyle.setAge(athlete.getAge());
                fiftyMFreeStyle.setDistrict(athlete.getDistrict());
                fiftyMFreeStyleService.saveFiftyMFreeStyle(fiftyMFreeStyle);
            }
    
            if (athlete.getFiftyMBackstroke()) {
                FiftyMBackstroke fiftyMBackstroke = new FiftyMBackstroke();
                fiftyMBackstroke.setAthleteId(athlete);
                fiftyMBackstroke.setGender(athlete.getGender());
                fiftyMBackstroke.setAge(athlete.getAge());
                fiftyMBackstroke.setDistrict(athlete.getDistrict());
                fiftyMBackstrokeService.saveFiftyMBackstroke(fiftyMBackstroke);
            }
    
            if (athlete.getFiftyMBreaststroke()) {
                FiftyMBreaststroke fiftyMBreaststroke = new FiftyMBreaststroke();
                fiftyMBreaststroke.setAthleteId(athlete);
                fiftyMBreaststroke.setGender(athlete.getGender());
                fiftyMBreaststroke.setAge(athlete.getAge());
                fiftyMBreaststroke.setDistrict(athlete.getDistrict());
                fiftyMBreaststrokeService.saveFiftyMBreaststroke(fiftyMBreaststroke);
            }
    
            if (athlete.getFiftyMButterflystroke()) {
                FiftyMButterflystroke fiftyMButterflystroke = new FiftyMButterflystroke();
                fiftyMButterflystroke.setAthleteId(athlete);
                fiftyMButterflystroke.setGender(athlete.getGender());
                fiftyMButterflystroke.setAge(athlete.getAge());
                fiftyMButterflystroke.setDistrict(athlete.getDistrict());
                fiftyMButterflystrokeService.saveFiftyMButterflystroke(fiftyMButterflystroke);
            }
    
            if (athlete.getHundredMFreestyle()) {
                HundredMFreestyle hundredMFreestyle = new HundredMFreestyle();
                hundredMFreestyle.setAthleteId(athlete);
                hundredMFreestyle.setGender(athlete.getGender());
                hundredMFreestyle.setAge(athlete.getAge());
                hundredMFreestyle.setDistrict(athlete.getDistrict());
                hundredMFreestyleService.saveHundredMFreestyle(hundredMFreestyle);
            }
    
            if (athlete.getHundredMBreaststroke()) {
                HundredMBreaststroke hundredMBreaststroke = new HundredMBreaststroke();
                hundredMBreaststroke.setAthleteId(athlete);
                hundredMBreaststroke.setGender(athlete.getGender());
                hundredMBreaststroke.setAge(athlete.getAge());
                hundredMBreaststroke.setDistrict(athlete.getDistrict());
                hundredMBreaststrokeService.saveHundredMBreaststroke(hundredMBreaststroke);
            }
    
            if (athlete.getFiftyMKickBoard()) {
                FiftyMKickBoard fiftyMKickBoard = new FiftyMKickBoard();
                fiftyMKickBoard.setAthleteId(athlete);
                fiftyMKickBoard.setGender(athlete.getGender());
                fiftyMKickBoard.setAge(athlete.getAge());
                fiftyMKickBoard.setDistrict(athlete.getDistrict());
                fiftyMKickBoardService.saveFiftyMKickBoard(fiftyMKickBoard);
            }
          
            return new ResponseEntity<>(athlete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/batchUpdateAthlete")
    public String batchUpdateAthlete() {
        List<AthleteDto> athleteList = athleteService.listAthleteDto();
        for(AthleteDto athlete : athleteList) {
            updateAthlete(athlete);
        }
        return "success";
    }

    @DeleteMapping("/deleteAthlete")
    public ResponseEntity<HttpStatus> deleteAthlete(@RequestParam(name = "athleteId", required = true) Integer athleteId) {
        try {
            athleteService.deleteAthlete(athleteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
