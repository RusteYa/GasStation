package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.entities.News;
import com.kpfu.itis.gasstation.forms.client.LoginFormClient;
import com.kpfu.itis.gasstation.forms.client.NewsFormClient;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
import com.kpfu.itis.gasstation.service.entities.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rustem.
 */
@RestController
@RequestMapping(value = "/client")
public class ClientAppController {
    private final AppUserService appUserService;
    private final NewsService newsService;

    @Autowired
    public ClientAppController(AppUserService appUserService, NewsService newsService) {
        this.appUserService = appUserService;
        this.newsService = newsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    @ResponseBody
    public AppUser login(@RequestBody LoginFormClient loginFormClient) {
        AppUser appUser = appUserService.getAppUserByLoginAndPassword(loginFormClient.getLogin(), loginFormClient.getPassword());
        return appUser;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public List<News> news() {
        List<News> newsList = newsService.getAllNews();
        return newsList;
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public void addNews(@RequestBody NewsFormClient newsFormClient) {
        newsService.saveNewsFromNewsFormClient(newsFormClient);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public void updateNews(@PathVariable("id") int id, @RequestBody NewsFormClient newsFormClient) {
        newsService.updateNewsFromNewsFormClientById(id, newsFormClient);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    @ResponseBody
    public News updateNews(@PathVariable("id") int id) {
        return newsService.getNewsById(id);
    }

    @RequestMapping(value = "/news/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public void deleteNews(@PathVariable("id") int id) {
        System.out.println(id);
        newsService.deleteNewsWithId(id);
    }
}
