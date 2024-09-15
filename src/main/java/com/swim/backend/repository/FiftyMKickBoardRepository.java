package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMKickBoard;

@Repository
public interface FiftyMKickBoardRepository extends JpaRepository<FiftyMKickBoard, String>{

    List<FiftyMKickBoard> findAll(Specification<FiftyMKickBoard> specification, Sort sort);

    List<FiftyMKickBoard> findByGenderAndAgeOrderByTimeAsc(String gender, Integer age);

    FiftyMKickBoard findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}