package com.swim.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swim.backend.model.News;
import com.swim.backend.repository.NewsRepository;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

  @Autowired
  private NewsRepository newsRepository;

  @GetMapping("/list")
  public List<News> listNews() {
    return newsRepository.findAll();
  }

  @GetMapping("/{id}")
  public News getNewsById(@PathVariable String id) {
    return newsRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  @PostMapping("/add")
  public ResponseEntity<News> addNews(@RequestBody News news) {
    try {
        News newNews = newsRepository.save(news);
      return new ResponseEntity<>(newNews, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<News> updateNews(@PathVariable String id, @RequestBody News newNews) {
    Optional<News> newsData = newsRepository.findById(id);

    if (newsData.isPresent()) {
      News updateNews = newsData.get();
      if (newNews.getDate() != null) {
        updateNews.setDate(newNews.getDate());
      }
      if (newNews.getTitle() != null) {
        updateNews.setTitle(newNews.getTitle());
      }
      if (newNews.getContent() != null) {
        updateNews.setContent(newNews.getContent());
      }
      return new ResponseEntity<>(newsRepository.save(updateNews), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteNews(@PathVariable String id) {
    try {
        newsRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}