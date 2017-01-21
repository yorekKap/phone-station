package com.phone.station.web.security;

import com.phone.station.entities.User;
import com.phone.station.entities.enums.Role;

/**
 * Representation of the user authorized in the system
 * <p>
 * The real {@link User} object isn't stored in session, because of possibility of
 * cache aging.
 * @author yuri
 *
 */
public class UserPrincipal {
	private String username;
	private Role role;
	private Long id;

	public UserPrincipal(String username, Role role, Long id) {
		this.username = username;
		this.role = role;
		this.id = id;
	}

	public void setName(String name){
		username = name;
	}

	public String getName() {
		return username;
	}

	public Role getRole(){
		return role;
	}

	public void setRole(Role role){
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
