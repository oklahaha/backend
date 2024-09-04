package com.swim.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swim.backend.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, String>{

    List<News> findAll(Specification<News> specification, Sort sort);

    News findByNewsId(String newsId);
}