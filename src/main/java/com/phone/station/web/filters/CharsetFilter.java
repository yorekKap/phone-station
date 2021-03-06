package com.phone.station.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter that sets request and response character encoding to UTF-8 
 * 
 * @author yuri
 *
 */
public class CharsetFilter implements Filter {

	private String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");
		if(encoding == null) encoding = "UTF-8";
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		  String codeRequest = request.getCharacterEncoding();

	        if(encoding != null && !encoding.equals(codeRequest)) {
	            request.setCharacterEncoding(encoding);
	            response.setCharacterEncoding(encoding);
	        }

		chain.doFilter(request, response);
	}



}
