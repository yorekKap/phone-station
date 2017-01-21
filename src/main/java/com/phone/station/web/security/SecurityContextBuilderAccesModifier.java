package com.phone.station.web.security;

import com.phone.station.entities.enums.Role;
import com.phone.station.web.security.SecurityContext.SecurityContextBuilder;

/**
 * Interface for convenient {@link SecurityContext} building
 *
 * @author yuri
 *
 */
public interface SecurityContextBuilderAccesModifier {

	SecurityContextBuilder hasRole(Role... role);
	SecurityContextBuilder authenticated();
	SecurityContextBuilder permitAll();

}
