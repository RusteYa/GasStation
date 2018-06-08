package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.News;
import com.kpfu.itis.gasstation.forms.NewsForm;
import com.kpfu.itis.gasstation.repositories.NewsRepository;
import com.kpfu.itis.gasstation.service.entities.NewsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {
    @Autowired
    private NewsService newsService;

    @MockBean
    private NewsRepository newsRepositoryMock;

    private List<News> newsList = new ArrayList<>();
    private News news = new News();;

    @Before
    public void setUp() {
        news.setId(0);
        news.setHeader("header");
        newsList.add(news);
    }

    @Test
    public void testGetAllNewss() {
        when(newsRepositoryMock.findAll()).thenReturn(newsList);

        List<News> newsList2 =  newsService.getAllNews();
        Assert.assertTrue(newsList.containsAll(newsList2) && newsList2.containsAll(newsList));
    }

    @Test
    public void testSaveNewsFromNewsForm() {
        when(newsRepositoryMock.findById(0)).thenReturn(news);
        when(newsRepositoryMock.save(any(News.class))).thenReturn(news);

        NewsForm newsForm = newsService.createNewsFormFromNewsById(0);
        newsForm.setFileDatas(new MultipartFile[0]);
        News news1 = newsService.saveNewsFromNewsForm(newsForm);
        Assert.assertEquals(newsForm.getHeader(), news1.getHeader());
    }

    @Test
    public void testDeleteNewsWithId() {
        when(newsRepositoryMock.deleteNewsById(0)).thenReturn(0);

        Assert.assertTrue(0 == newsService.deleteNewsWithId(0));
    }

    @Test
    public void testCreateNewsFormFromNewsById() {
        when(newsRepositoryMock.findById(0)).thenReturn(news);

        Assert.assertEquals(news.getHeader(), newsService.createNewsFormFromNewsById(0).getHeader());
    }

    @Test
    public void testUpdateNewsFromNewsFormById() {
        when(newsRepositoryMock.findById(0)).thenReturn(news);
        when(newsRepositoryMock.save(any(News.class))).thenReturn(news);

        NewsForm newsForm = newsService.createNewsFormFromNewsById(0);
        newsForm.setHeader("news_header");
        newsForm.setFileDatas(new MultipartFile[0]);
        News news1 = newsService.updateNewsFromNewsFormById(0, newsForm);
        Assert.assertEquals(news.getHeader(), news1.getHeader());
    }
}
