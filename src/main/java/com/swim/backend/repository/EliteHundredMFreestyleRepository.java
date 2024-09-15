package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteHundredMFreestyle;

@Repository
public interface EliteHundredMFreestyleRepository extends JpaRepository<EliteHundredMFreestyle, String>{

    List<EliteHundredMFreestyle> findAll(Specification<EliteHundredMFreestyle> specification, Sort sort);

    List<EliteHundredMFreestyle> findByGenderAndAgeOrderByTimeAsc(String gender, Integer age);

    EliteHundredMFreestyle findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}