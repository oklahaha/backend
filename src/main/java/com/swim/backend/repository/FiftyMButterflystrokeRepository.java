package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMButterflystroke;

@Repository
public interface FiftyMButterflystrokeRepository extends JpaRepository<FiftyMButterflystroke, String>{

    List<FiftyMButterflystroke> findAll(Specification<FiftyMButterflystroke> specification, Sort sort);

    FiftyMButterflystroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}