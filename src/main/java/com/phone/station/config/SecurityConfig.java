package com.phone.station.config;

import com.phone.station.web.security.SecurityContext;

public class SecurityConfig implements Config{

	@Override
	public void init(Components components) {
		components.add(SecurityContext.class, getSecurityContext());

	}

	private SecurityContext getSecurityContext(){
		return SecurityContext.createBuilder()
											  .forUrl("/login").permitAll()
											  .forUrl("/register").permitAll()
											  .forMatch("/resources/*").permitAll()
											  .forAny().authenticated()
											  .setRedirectUrl("/login")
											  .build();
	}
}
