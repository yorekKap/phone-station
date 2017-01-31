package com.phone.station.web.controllers.admin;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.phone.station.entities.News;
import com.phone.station.service.interfaces.NewsService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.utils.PageIndexFetcher;
import com.phone.station.web.dispatcher.Controller;
import com.phone.station.web.paginator.Paginator;
import com.phone.station.web.paginator.records.NewsRecordsCollection;

public class AdminController extends Controller {
	private static final Logger log = Logger.getLogger(AdminController.class);

	private final String ADMIN_USERNAME = "Admin";
	private final String PHOTO_DIRECTORY_PATH = "resources/img/news";

	private final int NUM_OF_RECORDS_PER_PAGE = 5;

	UserService userService;
	NewsService newsService;
	Paginator<News> paginator;

	public AdminController(UserService userService, NewsService newsService) {
		this.userService = userService;
		this.newsService = newsService;
		this.paginator = new Paginator<>(NUM_OF_RECORDS_PER_PAGE, new NewsRecordsCollection(newsService));
	}

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		int pageIndex = PageIndexFetcher.getPageIndex(request);
		request.setAttribute("admin", userService.findByUsername(ADMIN_USERNAME));
		request.setAttribute("newsPage", paginator.findPage(pageIndex));

		return "admin";
	}

	@Override
	public String post(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part photo = null;
		try{
			photo = request.getPart("photo");

			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			news.setDate(new Date());

			if(! newsService.persist(news) ){
				log.error("News is not added, creation failed");
			}

			photo.write(getFilePath(news.getId(), request));

			response.sendRedirect("/admin");

		}catch(Exception e){
			log.error("Error while writing photo to the file");
		}

		return null;
	}

	public String getFilePath(Long id, HttpServletRequest request){
		String applicationPath = request.getServletContext().getRealPath("");
	    String uploadFilePath = applicationPath + File.separator + PHOTO_DIRECTORY_PATH;
	    return uploadFilePath + File.separator + id + ".jpg";
	}

}
