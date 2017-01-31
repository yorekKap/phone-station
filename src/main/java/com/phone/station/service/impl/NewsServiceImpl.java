package com.phone.station.service.impl;

import java.util.Date;
import java.util.List;

import com.phone.station.dao.interfaces.NewsDao;
import com.phone.station.entities.News;
import com.phone.station.service.interfaces.NewsService;

public class NewsServiceImpl implements NewsService {

	NewsDao newsDao;

	public NewsServiceImpl(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public boolean persist(News news) {
		return newsDao.persist(news);
	}

	@Override
	public List<News> findAll() {
		return newsDao.findAll();
	}

	@Override
	public List<News> findAll(int offset, int limit) {
		return newsDao.findAll(offset, limit);
	}

	@Override
	public boolean createNews(String title, String content) {
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setDate(new Date());

		return newsDao.persist(news);
	}

	@Override
	public void update(News news) {
		newsDao.update(news);
	}

	@Override
	public void delete(News news) {
		newsDao.delete(news);
	}

	@Override
	public int getNumOfNews() {
		return newsDao.getNumOfRecords();
	}


}
