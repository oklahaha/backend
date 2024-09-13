package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;
import com.swim.backend.model.EliteFiftyMBreaststroke;

@Repository
public interface EliteFiftyMBreaststrokeRepository extends JpaRepository<EliteFiftyMBreaststroke, String>{

    List<EliteFiftyMBreaststroke> findAll(Specification<EliteFiftyMBreaststroke> specification, Sort sort);

    List<EliteFiftyMBreaststroke> findByGenderAndAge(String gender, Integer age);

    EliteFiftyMBreaststroke findByAthleteId(Athlete athleteId);

    void deleteByAthleteId(Athlete athleteId);

}