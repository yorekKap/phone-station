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
import com.phone.station.entities.enums.Role;
import com.phone.station.utils.ContextPathFetcher;
import com.phone.station.web.security.SecurityContext;
import com.phone.station.web.security.UserPrincipal;

/**
 * Check if particular resource is available for current user session.
 *
 * @author yuri
 *
 */
public class SecurityFilter implements Filter {

	private static final String PRINCIPAL = "principal";

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
		String url = ContextPathFetcher.getContextPath(httpRequest);
		UserPrincipal principal = (UserPrincipal)httpRequest.getSession().getAttribute(PRINCIPAL);
		Role role = null;

		if(principal == null){
			role = Role.NOT_AUTHNENTICATED;
		}
		else{
			role = principal.getRole();
		}

		if (securityContext.hasAccess(url, role)) {
			filterChain.doFilter(request, response);
		}
		else{
			String redirectUrl = securityContext.getRedirectUrl(role);
			((HttpServletResponse)response).sendRedirect(redirectUrl);
		}

	}


}
