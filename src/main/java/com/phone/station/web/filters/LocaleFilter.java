package com.phone.station.web.filters;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.phone.station.config.WebAppContext;
import com.phone.station.web.resolvers.LocaleResolver;

/**
 * Filter that sets right locale based on the request parameter 
 * or session attribute {@code lang}
 *
 * @author yuri
 *
 */
public class LocaleFilter implements Filter {

	private static final String LANG_PARAMETER = "lang";
	private static final String LOCALE_ATTRIBUTE = "locale";

	LocaleResolver localeResolver = WebAppContext.get(LocaleResolver.class);

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;

		String lang = httpRequest.getParameter(LANG_PARAMETER);
		Locale locale = null;
		
		if (lang != null) {
			locale = localeResolver.getLocale(lang);
		}
		else{
			locale = (Locale)httpRequest.getSession().getAttribute(LOCALE_ATTRIBUTE);
			if(locale == null){
				locale = localeResolver.getDefaultLocale();
			}
		}
		httpRequest.getSession().setAttribute(LOCALE_ATTRIBUTE, locale);
		chain.doFilter(request, response);
	}

}
