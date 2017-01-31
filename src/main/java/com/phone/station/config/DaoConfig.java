package com.phone.station.config;


import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.phone.station.dao.factories.DataSourceFactory;
import com.phone.station.dao.implementation.jdbc.JdbcNewsDao;
import com.phone.station.dao.implementation.jdbc.JdbcPaymentDao;
import com.phone.station.dao.implementation.jdbc.JdbcServiceDao;
import com.phone.station.dao.implementation.jdbc.JdbcUserDao;
import com.phone.station.dao.implementation.jdbc.JdbcTariffDao;
import com.phone.station.dao.interfaces.NewsDao;
import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.interfaces.UserDao;

/**
 * {@link Config} implementation for fulfilling components context
 * with dao implementations
 *
 * @author yuri
 *
 */
public class DaoConfig implements Config{
	private static final Logger log = Logger.getLogger(DaoConfig.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		DataSource dataSource = DataSourceFactory.getDataSource(DataSourceFactory.MY_SQL);

		components
			.add(ServiceDao.class,    new JdbcServiceDao(dataSource))
			.add(UserDao.class, 	  new JdbcUserDao(dataSource))
			.add(TariffDao.class,     new JdbcTariffDao(dataSource))
			.add(PaymentDao.class,    new JdbcPaymentDao(dataSource))
			.add(NewsDao.class, 	  new JdbcNewsDao(dataSource));

		log.info("Dao components added");
	}
}
