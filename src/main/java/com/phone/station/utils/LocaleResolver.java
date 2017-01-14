package com.phone.station.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleResolver {

	Map<String, Locale> locales;
	Locale defaultLocale;

	private LocaleResolver() {
		 locales = new HashMap<>();
		 defaultLocale = null;
	}

	private LocaleResolver(Map<String, Locale> locales, Locale defaultLocale) {
		this.locales = locales;
		this.defaultLocale = defaultLocale;
	}

	public Locale getDefaultLocale(){
		return defaultLocale;
	}

	public Locale getLocale(String lang) {
		Locale locale = null;
		locale = locales.get(lang);

		if(locale == null){
			return defaultLocale;
		}

		return locale;
	}

	public static LocaleResolverBuilder getBuilder() {
		return new LocaleResolverBuilder();
	}

	public static class LocaleResolverBuilder {

		private Map<String, Locale> locales = new HashMap<>();
		private Locale defaultLocale;

		private LocaleResolverBuilder() {
		}

		public LocaleResolverBuilder setDefaultLocale(Locale locale) {
			this.defaultLocale = locale;
			return this;
		}

		public LocaleResolverBuilder addLocale(String lang, Locale locale) {
			locales.put(lang, locale);
			return this;
		}

		public LocaleResolver createLocaleResolver(){
			if(defaultLocale == null){
				throw new RuntimeException("The default locale must be set in LocaleResolver");
			}

			return new LocaleResolver(locales, defaultLocale);
		}

	}
}
