package com.phone.station.web.security;

import com.phone.station.entities.enums.Role;
import com.phone.station.web.security.SecurityContext.SecurityContextBuilder;

public interface SecurityContextBuilderAccesModifier {

	SecurityContextBuilder hasRole(Role role);
	SecurityContextBuilder authenticated();
	SecurityContextBuilder permitAll();

}
