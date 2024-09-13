package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBackstroke;

@Repository
public interface EliteFiftyMBackstrokeRepository extends JpaRepository<EliteFiftyMBackstroke, String>{

    List<EliteFiftyMBackstroke> findAll(Specification<EliteFiftyMBackstroke> specification, Sort sort);

    List<EliteFiftyMBackstroke> findByGenderAndAge(String gender, Integer age);

    EliteFiftyMBackstroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}