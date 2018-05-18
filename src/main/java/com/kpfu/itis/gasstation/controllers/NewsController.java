package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.News;
import com.kpfu.itis.gasstation.forms.NewsForm;
import com.kpfu.itis.gasstation.repositories.NewsRepository;
import com.kpfu.itis.gasstation.service.UploadService;
import com.kpfu.itis.gasstation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class NewsController {
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final UploadService uploadService;

    @Autowired
    public NewsController(NewsRepository newsRepository, UserService userService, UploadService uploadService) {
        this.newsRepository = newsRepository;
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news(ModelMap model) {
        userService.addUserToModel(model);
        List<News> newslist = newsRepository.findAll();
        model.put("newslist", newslist);
        return "news";
    }

    @RequestMapping(value = "/contentmanager/news/add", method = RequestMethod.GET)
    public String addNews(ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Добавить");

        NewsForm newsForm = new NewsForm();
        model.put("newsForm", newsForm);
        return "create_update_news";
    }

    @RequestMapping(value = "/contentmanager/news/add", method = RequestMethod.POST)
    public String addNews(@Valid NewsForm newsForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            String photoPath = uploadService.upload(newsForm.getFileDatas());

            News news = new News();
            news.setHeader(newsForm.getHeader());
            news.setBody(newsForm.getBody());
            news.setPhotoPath(photoPath);
            news.setDate(new Date());

            newsRepository.save(news);

            return "redirect:/news";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Добавить");

            model.put("newsForm", newsForm);
            return "create_update_news";
        }
    }

    @RequestMapping(value = "/contentmanager/news/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteNews(@PathVariable("id") int id) {
        newsRepository.deleteNewsById(id);
        return "redirect:/news";
    }

    @RequestMapping(value = "/contentmanager/news/{id}", method = RequestMethod.GET)
    public String updateNews(@PathVariable("id") int id, ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Изменить");

        News news = newsRepository.findById(id);
        NewsForm newsForm = new NewsForm();
        newsForm.setHeader(news.getHeader());
        newsForm.setBody(news.getBody());
        model.put("newsForm", newsForm);
        return "create_update_news";
    }

    @RequestMapping(value = "/contentmanager/news/{id}", method = RequestMethod.POST)
    public String updateNews(@PathVariable("id") int id, ModelMap model, @Valid NewsForm newsForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            News news = newsRepository.findById(id);
            String photoPath = uploadService.upload(newsForm.getFileDatas());

            news.setHeader(newsForm.getHeader());
            news.setBody(newsForm.getBody());
            news.setDate(new Date());
            if (!"".equals(photoPath)) {
                news.setPhotoPath(photoPath);
            }

            newsRepository.save(news);

            return "redirect:/news";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Изменить");

            model.addAttribute("newsForm", newsForm);
            return "create_update_news";
        }
    }
}