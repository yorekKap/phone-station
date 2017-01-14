package com.phone.station.config;

import java.util.Locale;

import com.phone.station.utils.LocaleResolver;

public class RootConfig implements Config {

	@Override
	public void init(Components components) {
		components.add(LocaleResolver.class, getLocaleResolver());
	}

	public LocaleResolver getLocaleResolver(){
		return LocaleResolver.getBuilder()
										  .setDefaultLocale(Locale.ENGLISH)
										  .addLocale("ua", new Locale("ua", "UA"))
										  .createLocaleResolver();
	}

}
