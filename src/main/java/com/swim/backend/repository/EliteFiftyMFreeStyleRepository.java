package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMFreeStyle;

@Repository
public interface EliteFiftyMFreeStyleRepository extends JpaRepository<EliteFiftyMFreeStyle, String>{

    List<EliteFiftyMFreeStyle> findAll(Specification<EliteFiftyMFreeStyle> specification, Sort sort);

    List<EliteFiftyMFreeStyle> findByGenderAndAge(String gender, Integer age);

    EliteFiftyMFreeStyle findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}