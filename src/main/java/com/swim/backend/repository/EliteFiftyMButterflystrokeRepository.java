package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMButterflystroke;

@Repository
public interface EliteFiftyMButterflystrokeRepository extends JpaRepository<EliteFiftyMButterflystroke, String>{

    List<EliteFiftyMButterflystroke> findAll(Specification<EliteFiftyMButterflystroke> specification, Sort sort);

    List<EliteFiftyMButterflystroke> findByGenderAndAge(String gender, Integer age);

    EliteFiftyMButterflystroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}