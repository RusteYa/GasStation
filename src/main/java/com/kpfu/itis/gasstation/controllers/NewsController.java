package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.News;
import com.kpfu.itis.gasstation.forms.NewsForm;
import com.kpfu.itis.gasstation.service.entities_service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news(ModelMap model) {
        List<News> newslist = newsService.getAllNews();
        model.put("newslist", newslist);
        return "news";
    }

    @RequestMapping(value = "/contentmanager/news/add", method = RequestMethod.GET)
    public String addNews(ModelMap model) {
        model.put("status", "Добавить");
        NewsForm newsForm = new NewsForm();
        model.put("newsForm", newsForm);
        return "create_update_news";
    }

    @RequestMapping(value = "/contentmanager/news/add", method = RequestMethod.POST)
    public String addNews(@Valid NewsForm newsForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            newsService.saveNewsFromNewsForm(newsForm);
            return "redirect:/news";
        } else {
            model.put("status", "Добавить");
            model.put("newsForm", newsForm);
            return "create_update_news";
        }
    }

    @RequestMapping(value = "/contentmanager/news/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteNews(@PathVariable("id") int id) {
        newsService.deleteNewsWithId(id);
        return "redirect:/news";
    }

    @RequestMapping(value = "/contentmanager/news/{id}", method = RequestMethod.GET)
    public String updateNews(@PathVariable("id") int id, ModelMap model) {
        model.put("status", "Изменить");
        NewsForm newsForm = newsService.createNewsFormFromNewsById(id);
        model.put("newsForm", newsForm);
        return "create_update_news";
    }

    @RequestMapping(value = "/contentmanager/news/{id}", method = RequestMethod.POST)
    public String updateNews(@PathVariable("id") int id, ModelMap model, @Valid NewsForm newsForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            newsService.updateNewsFromNewsFormById(id, newsForm);
            return "redirect:/news";
        } else {
            model.put("status", "Изменить");
            model.addAttribute("newsForm", newsForm);
            return "create_update_news";
        }
    }
}