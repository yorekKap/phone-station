package com.phone.station.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Session;

import com.phone.station.config.WebAppContext;
import com.phone.station.entities.User;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.security.UserPrincipal;


/**
 * Allow to get current {@link User} based on the {@code username} of the
 * {@link UserPrincipal} from {@link Session} of {@link HttpServletRequest}
 * <p>
 * The answer to question "Why can't we just store {@link User} in {@link Session}?"
 * is because of cache aging.
 *
 * @author yuri
 *
 */
public class CurrentUserFetcher {

	/**
	 * Get {@link User} currently authorized from {@link HttpServletRequest}
	 *
	 * @param request from which @link
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request, UserService userService){
		String username = ((UserPrincipal)request.getSession().getAttribute("principal")).getName();
		return userService.findByUsername(username);
	}

	/**
	 * Get current {@link User} {@code id}
	 *
	 * @param request
	 * @return {@code id} of currently authorized {@link User}
	 */
	public static Long getCurrentUserId(HttpServletRequest request){
		return ((UserPrincipal)(request.getSession().getAttribute("principal"))).getId();
	}
}
