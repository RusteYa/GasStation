package controllers;

import entities.News;
import helpers.AppContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.NewsRepository;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllNews(ModelMap model) {
        NewsRepository newsRepository = AppContext.getApplicationContext().getBean(NewsRepository.class);
        List<News> news = newsRepository.findAll();
        model.put("news", news);
        return "views/news/newslist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getNews(@PathVariable("id") int id, ModelMap model) {
        NewsRepository newsRepository = AppContext.getApplicationContext().getBean(NewsRepository.class);
        News news = newsRepository.findById(id);
        model.put("news", news);
        return "views/news/news";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addNews(@RequestParam String body) {
        NewsRepository newsRepository = AppContext.getApplicationContext().getBean(NewsRepository.class);
        News news = new News();
        news.setBody(body);
        newsRepository.save(news);
        return "redirect:news";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeNews(@PathVariable("id") int id, ModelMap model, @RequestParam String body) {
        NewsRepository newsRepository = AppContext.getApplicationContext().getBean(NewsRepository.class);
        News news = newsRepository.findById(id);
        news.setBody(body);
        newsRepository.deleteNewsById(id);
        newsRepository.save(news);
        model.put("news", news);
        return "redirect:/news/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteMessage(@PathVariable("id") int id) {
        NewsRepository newsRepository = AppContext.getApplicationContext().getBean(NewsRepository.class);
        newsRepository.deleteNewsById(id);
        return "redirect:news";
    }
}