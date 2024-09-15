package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMKickBoard;
import com.swim.backend.repository.FiftyMKickBoardRepository;

@Service
public class FiftyMKickBoardService {

    @Autowired
    private FiftyMKickBoardRepository fiftyMKickBoardRepository;

    public FiftyMKickBoard saveFiftyMKickBoard(FiftyMKickBoard fiftyMKickBoard) {
        return fiftyMKickBoardRepository.save(fiftyMKickBoard);
    }

    public List<FiftyMKickBoard> saveFiftyMKickBoardList(List<FiftyMKickBoard> fiftyMKickBoardList) {
        return fiftyMKickBoardRepository.saveAll(fiftyMKickBoardList);
    }
    
    public List<FiftyMKickBoard> listFiftyMKickBoard() {
        return fiftyMKickBoardRepository.findAll();
    }

    public List<FiftyMKickBoard> listFiftyMKickBoardByGenderAndAge(String gender, Integer age) {
        return fiftyMKickBoardRepository.findByGenderAndAgeOrderByTimeAsc(gender, age);
    }

    public FiftyMKickBoard getFiftyMKickBoard(String id) {
        return fiftyMKickBoardRepository.findById(id).orElse(null);
    }

    public FiftyMKickBoard saveOrUpdateFiftyMKickBoard(FiftyMKickBoard fiftyMKickBoard) {
        return fiftyMKickBoardRepository.save(fiftyMKickBoard);
    }

    @Transactional
    public String deleteFiftyMKickBoard(String id) {
        fiftyMKickBoardRepository.deleteById(id);
        return "FiftyMKickBoard removed";
    }

    @Transactional
    public String deleteFiftyMKickBoardByAthlete(Athlete athleteId) {
        fiftyMKickBoardRepository.deleteByAthleteId(athleteId);
        return "FiftyMKickBoard removed";
    }

    @Transactional
    public String deleteAllFiftyMKickBoard(List<String> ids) {
        fiftyMKickBoardRepository.deleteAllById(ids);
        return "FiftyMKickBoard entries removed";
    }
}