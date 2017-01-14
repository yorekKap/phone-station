package com.phone.station.config;

import javax.sql.DataSource;

import com.phone.station.dao.factories.DataSourceFactory;
import com.phone.station.dao.implementation.jdbc.JdbcPaymentDao;
import com.phone.station.dao.implementation.jdbc.JdbcServiceBidDao;
import com.phone.station.dao.implementation.jdbc.JdbcServiceDao;
import com.phone.station.dao.implementation.jdbc.JdbcUserDao;
import com.phone.station.dao.implementation.jdbc.JdbcTariffDao;
import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.ServiceBidDao;
import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.entities.Tariff;

public class DaoConfig implements Config{

	@Override
	public void init(Components components) {
		DataSource dataSource = DataSourceFactory.getDataSource(DataSourceFactory.MY_SQL);

		components
			.add(ServiceBidDao.class, new JdbcServiceBidDao(dataSource))
			.add(ServiceDao.class,    new JdbcServiceDao(dataSource))
			.add(UserDao.class, 	  new JdbcUserDao(dataSource))
			.add(TariffDao.class,     new JdbcTariffDao(dataSource))
			.add(PaymentDao.class,    new JdbcPaymentDao(dataSource));
	}
}
