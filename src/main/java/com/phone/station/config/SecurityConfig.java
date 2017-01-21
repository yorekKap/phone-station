package com.phone.station.config;


import org.apache.log4j.Logger;

import com.phone.station.entities.enums.Role;
import com.phone.station.web.security.SecurityContext;

/**
 * {@link Config} implementation for fulfilling components context
 * with application security components
 *
 * @author yuri
 *
 */
public class SecurityConfig implements Config{
		private static final String LOGOUT = "/logout";

		private static final Logger log = Logger.getLogger(SecurityConfig.class);

		//urls
		private static final String LOGIN = "/login";
		private static final String REGISTER = "/register";
		private static final String WELCOME = "/welcome";
		private static final String HOME = "/home";
		private static final String ADMIN = "/admin";

		//url patterns
		private static final String RESOURCES_PATTERN = "/resources/*";
		private static final String ADMIN_PATTERN = "/admin*";



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		components.add(SecurityContext.class, getSecurityContext());

		log.info("Security components added");
	}



	/**
	 * Initialize {@link SecurityContext}
	 *
	 * @return @{link SecurityContext} instance
	 */
	private SecurityContext getSecurityContext(){
		return SecurityContext.createBuilder()
											  .forUrl(LOGIN).hasRole(Role.NOT_AUTHNENTICATED)
											  .forUrl(REGISTER).hasRole(Role.NOT_AUTHNENTICATED)
											  .forUrl(LOGOUT).hasRole(Role.ADMIN, Role.USER)
											  .forMatch(RESOURCES_PATTERN).permitAll()
											  .forMatch(ADMIN_PATTERN).hasRole(Role.ADMIN)
											  .forAny().authenticated()
											  .addRedirectUrlForRole(Role.NOT_AUTHNENTICATED, LOGIN)
											  .addRedirectUrlForRole(Role.USER, HOME)
											  .addRedirectUrlForRole(Role.ADMIN, ADMIN)
											  .setDefaultRedirectUrl(WELCOME)
											  .build();
	}
}
