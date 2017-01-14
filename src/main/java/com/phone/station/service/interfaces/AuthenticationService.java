package com.phone.station.service.interfaces;

import com.phone.station.entities.User;
import com.phone.station.exceptions.service.AuthenticationException;
import com.phone.station.web.security.UserPrincipal;

public interface AuthenticationService {

	UserPrincipal login(String username, String password) throws AuthenticationException;
}
