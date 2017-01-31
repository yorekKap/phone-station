package com.phone.station.service.interfaces;

import java.util.List;

import com.phone.station.entities.News;

public interface NewsService {

	boolean persist(News news);

	List<News> findAll();

	List<News> findAll(int offset, int limit);

	boolean createNews(String title, String content);

	void update(News news);

	void delete(News news);

	int getNumOfNews();
}
