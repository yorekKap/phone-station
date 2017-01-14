package com.phone.station.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phone.station.config.WebAppContext;
import com.phone.station.web.security.SecurityContext;

public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		SecurityContext securityContext = WebAppContext.get(SecurityContext.class);

		HttpServletRequest httpRequest = (HttpServletRequest)request;

		if (securityContext.hasAcces(httpRequest)) {
			filterChain.doFilter(request, response);
		}
		else{
			((HttpServletResponse)response).sendRedirect("/login");
		}

	}


}
