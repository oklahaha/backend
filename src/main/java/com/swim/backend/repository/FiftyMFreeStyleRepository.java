package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.FiftyMFreeStyle;

@Repository
public interface FiftyMFreeStyleRepository extends JpaRepository<FiftyMFreeStyle, String>{

    List<FiftyMFreeStyle> findAll(Specification<FiftyMFreeStyle> specification, Sort sort);

    List<FiftyMFreeStyle> findByGenderAndAge(String gender, Integer age);

    FiftyMFreeStyle findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}