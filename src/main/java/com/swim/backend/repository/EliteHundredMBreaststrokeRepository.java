package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMBreaststroke;

@Repository
public interface EliteHundredMBreaststrokeRepository extends JpaRepository<EliteHundredMBreaststroke, String>{

    List<EliteHundredMBreaststroke> findAll(Specification<EliteHundredMBreaststroke> specification, Sort sort);

    List<EliteHundredMBreaststroke> findByGenderAndAgeOrderByTimeAsc(String gender, Integer age);

    EliteHundredMBreaststroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}