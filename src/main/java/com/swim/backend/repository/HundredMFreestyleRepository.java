package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.HundredMFreestyle;

@Repository
public interface HundredMFreestyleRepository extends JpaRepository<HundredMFreestyle, String>{

    List<HundredMFreestyle> findAll(Specification<HundredMFreestyle> specification, Sort sort);

    List<HundredMFreestyle> findByGenderAndAgeOrderByTimeAsc(String gender, Integer age);

    HundredMFreestyle findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}