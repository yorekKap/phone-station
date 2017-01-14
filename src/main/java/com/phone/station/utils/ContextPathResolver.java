package com.phone.station.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ContextPathResolver {

	public static String getContextPath(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		Pattern p = Pattern.compile("http://[^/]+");
		Matcher m = p.matcher(url);
		if(m.find()){
			url = url.substring(m.end(), url.length());
		}
		return url;
}
}
