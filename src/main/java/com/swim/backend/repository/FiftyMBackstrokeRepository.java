package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBackstroke;

@Repository
public interface FiftyMBackstrokeRepository extends JpaRepository<FiftyMBackstroke, String>{

    List<FiftyMBackstroke> findAll(Specification<FiftyMBackstroke> specification, Sort sort);

    FiftyMBackstroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}