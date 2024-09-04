package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.Athlete;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Integer>{

    List<Athlete> findAll(Specification<Athlete> specification, Sort sort);

    Athlete findByAthleteId(Integer athleteId);
}