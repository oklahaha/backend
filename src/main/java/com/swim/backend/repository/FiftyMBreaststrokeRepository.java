package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMBreaststroke;

@Repository
public interface FiftyMBreaststrokeRepository extends JpaRepository<FiftyMBreaststroke, String>{

    List<FiftyMBreaststroke> findAll(Specification<FiftyMBreaststroke> specification, Sort sort);

    FiftyMBreaststroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}