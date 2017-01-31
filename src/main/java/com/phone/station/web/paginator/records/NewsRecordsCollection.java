package com.phone.station.web.paginator.records;

import java.util.List;

import com.phone.station.entities.News;
import com.phone.station.service.interfaces.NewsService;
import com.phone.station.web.paginator.RecordsCollection;

public class NewsRecordsCollection implements RecordsCollection<News> {

	NewsService newsService;

	public NewsRecordsCollection(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	public List<News> getRecords(int offset, int limit) {
		return newsService.findAll(offset, limit);
	}

	@Override
	public int getNumOfRecords() {
		return newsService.getNumOfNews();
	}





}
