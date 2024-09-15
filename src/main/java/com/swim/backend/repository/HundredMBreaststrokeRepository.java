package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMBreaststroke;

@Repository
public interface HundredMBreaststrokeRepository extends JpaRepository<HundredMBreaststroke, String>{

    List<HundredMBreaststroke> findAll(Specification<HundredMBreaststroke> specification, Sort sort);

    List<HundredMBreaststroke> findByGenderAndAgeOrderByTimeAsc(String gender, Integer age);

    HundredMBreaststroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}