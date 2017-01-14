package com.phone.station.web.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.phone.station.entities.enums.Role;
import com.phone.station.exceptions.security.SecurityContextBuilderException;
import com.phone.station.utils.ContextPathResolver;

public class SecurityContext {

	private static final String PRINCIPAL_ATTRIBUTE = "principal";

	private Map<String, Role> urls;
	private Map<String, Role> matches;
 	private Role forAny = Role.NONE;
	private String redirectUrl;

	private SecurityContext(Map<String, Role> urls, Map<String, Role> matches, String redirectUrl) {
		this.urls = urls;
		this.matches = matches;
	}

	private SecurityContext(Map<String, Role> urls, Map<String, Role> matches, Role forAny,
							String redirectUrl) {
		this.urls = urls;
		this.matches = matches;
		this.forAny = forAny;
		this.redirectUrl = redirectUrl;
	}

	public boolean hasAcces(HttpServletRequest request){
		String url = ContextPathResolver.getContextPath(request);

		Role roleRequired;
		if((roleRequired = urls.get(url)) != null){
			return checkRole(roleRequired, request);
		}
		else if((roleRequired = getMatch(url)) != null){
			return checkRole(roleRequired, request);
		}
		else{
			return checkRole(forAny, request);
		}
	}

	private boolean checkRole(Role roleRequired, HttpServletRequest request){
		if(roleRequired == Role.NONE){
			return true;
		}
		Object principalObj = request.getSession().getAttribute(PRINCIPAL_ATTRIBUTE);

		if(principalObj == null){
			return false;
		}

		UserPrincipal principal = (UserPrincipal)principalObj;

		return principal.getRole().getPriority() >= roleRequired.getPriority();
	}

	private Role getMatch(String url){
		String key = matches.keySet().stream()
						.filter(x -> url.matches(x))
						.findFirst()
						.orElse(null);

		if(key == null){
			return null;
		}

		return matches.get(key);
	}

	public String getRedirectUrl(){
		return redirectUrl;
	}

	public static SecurityContextBuilder createBuilder(){
		return new SecurityContextBuilder();
	}

	public static class SecurityContextBuilder implements SecurityContextBuilderAccesModifier{

		private static final String MANDATORY_REDIRECT_URL_MESSAGE = "Redirect url must be set";

		private Map<String, Role> urls = new HashMap<>();
		private Map<String, Role> matches = new HashMap<>();
		private Role forAny = Role.NONE;
		private String redirectUrl;
		private String tempUrl;
		private String tempMatch;

		public SecurityContextBuilderAccesModifier forUrl(String url){
			this.tempUrl = url;
			return this;
		}

		public SecurityContextBuilderAccesModifier forMatch(String url){
			this.tempMatch = url.replace("*", ".*");
			return this;
		}

		public SecurityContextBuilderAccesModifier forAny(){
			return this;
		}

		public SecurityContextBuilder setRedirectUrl(String redirectUrl){
			this.redirectUrl = redirectUrl;
			return this;
		}

		@Override
		public SecurityContextBuilder hasRole(Role role) {
			putRoleToMap(role);
			clearTemps();
			return this;
		}

		@Override
		public SecurityContextBuilder authenticated() {
			putRoleToMap(Role.USER);
			clearTemps();
			return this;
		}

		@Override
		public SecurityContextBuilder permitAll() {
			putRoleToMap(Role.NONE);
			clearTemps();
			return this;
		}


		private void putRoleToMap(Role role){
			if (tempUrl != null){
				urls.put(tempUrl, role);
			}
			else if (tempMatch != null){
				matches.put(tempMatch, role);
			}
			else{
				forAny = role;
			}
		}

		private void clearTemps(){
			tempUrl = null;
			tempMatch = null;
		}

		public SecurityContext build(){
			if(redirectUrl == null){
				throw new SecurityContextBuilderException(MANDATORY_REDIRECT_URL_MESSAGE);
			}
			return new SecurityContext(urls, matches, forAny, redirectUrl);
		}
	}
}
