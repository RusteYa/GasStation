package com.kpfu.itis.gasstation.service.entities_service;

import com.kpfu.itis.gasstation.entities.News;
import com.kpfu.itis.gasstation.forms.NewsForm;
import com.kpfu.itis.gasstation.repositories.NewsRepository;
import com.kpfu.itis.gasstation.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UploadService uploadService;

    @Autowired
    public NewsService(NewsRepository newsRepository, UploadService uploadService) {
        this.newsRepository = newsRepository;
        this.uploadService = uploadService;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News saveNewsFromNewsForm(NewsForm newsForm) {
        String photoPath = uploadService.upload(newsForm.getFileDatas());

        News news = new News();
        news.setHeader(newsForm.getHeader());
        news.setBody(newsForm.getBody());
        news.setPhotoPath(photoPath);
        news.setDate(new Date());

        return newsRepository.save(news);
    }

    public int deleteNewsWithId(int id) {
        return newsRepository.deleteNewsById(id);
    }

    public NewsForm createNewsFormFromNewsById(int id) {
        News news = newsRepository.findById(id);
        NewsForm newsForm = new NewsForm();
        newsForm.setHeader(news.getHeader());
        newsForm.setBody(news.getBody());

        return newsForm;
    }

    public News updateNewsFromNewsFormById(int id, NewsForm newsForm) {
        News news = newsRepository.findById(id);
        String photoPath = uploadService.upload(newsForm.getFileDatas());

        news.setHeader(newsForm.getHeader());
        news.setBody(newsForm.getBody());
        news.setDate(new Date());
        if (!"".equals(photoPath)) {
            news.setPhotoPath(photoPath);
        }

        return newsRepository.save(news);
    }
}
