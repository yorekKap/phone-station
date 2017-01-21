package com.phone.station.config;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.phone.station.web.resolvers.LocaleResolver;

/**
 * {@link Config} implementation for fulfilling components context
 * with application auxiliary components
 *
 * @author yuri
 *
 */
public class RootConfig implements Config {
	private static final Logger log = Logger.getLogger(RootConfig.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		components.add(LocaleResolver.class, getLocaleResolver());

		log.info("Root components added");
	}

	/**
	 * Method for {@link LocaleResolver} initialization
	 *
	 * @return {@link LocaleResolver} instance
	 */
	public LocaleResolver getLocaleResolver(){
		return LocaleResolver.getBuilder()
										  .setDefaultLocale(Locale.ENGLISH)
										  .addLocale("ua", new Locale("ua", "UA"))
										  .createLocaleResolver();
	}

}
