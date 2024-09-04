package com.swim.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swim.backend.model.News;
import com.swim.backend.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public List<News> saveNews(List<News> news) {
        return newsRepository.saveAll(news);
    }
    
    public List<News> listNews() {
        return newsRepository.findAll();
    }

    public News getNews(String newsId) {
        return newsRepository.findById(newsId).orElse(null);
    }

    public News saveOrUpdateNews(News news) {
        return newsRepository.save(news);
    }

    public String deleteNews(String newsId){
        newsRepository.deleteById(newsId);
        return "News removed";
    }

    public String deleteAllNews(List<String> newsIds) {
        newsRepository.deleteAllById(newsIds);
        return "News removed";
    }
}
