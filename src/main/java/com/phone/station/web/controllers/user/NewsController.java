package com.phone.station.web.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.entities.News;
import com.phone.station.service.interfaces.NewsService;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.paginator.Paginator;
import com.phone.station.web.paginator.records.NewsRecordsCollection;

public class NewsController extends Controller {

	private static final int NUM_OF_RECORDS_PER_PAGE = 5;

	NewsService newsService;
	Paginator<News> paginator;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
		this.paginator = new Paginator<>(NUM_OF_RECORDS_PER_PAGE, new NewsRecordsCollection(newsService));
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String pageIndexStr = request.getParameter("page-index");
		int pageIndex = 1;
		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		request.setAttribute("newsPage", paginator.findPage(pageIndex));
		return "news";

	}



}
