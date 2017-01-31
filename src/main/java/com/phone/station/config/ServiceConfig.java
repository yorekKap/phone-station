package com.phone.station.config;

import org.apache.log4j.Logger;

import com.phone.station.dao.interfaces.NewsDao;
import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.service.impl.AuthenticationServiceImpl;
import com.phone.station.service.impl.NewsServiceImpl;
import com.phone.station.service.impl.PaymentServiceImpl;
import com.phone.station.service.impl.ServicesServiceImpl;
import com.phone.station.service.impl.TariffServiceImpl;
import com.phone.station.service.impl.UserServiceImpl;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.service.interfaces.NewsService;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.service.interfaces.UserService;

/**
 * {@link Config} implementation for fulfilling components context
 * with service implementations
 *
 * @author yuri
 *
 */
public class ServiceConfig implements Config {
		private static final Logger log = Logger.getLogger(ServiceConfig.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		UserDao userDao = WebAppContext.get(UserDao.class);
		TariffDao tariffDao = WebAppContext.get(TariffDao.class);
		ServiceDao serviceDao = WebAppContext.get(ServiceDao.class);
		PaymentDao paymentDao = WebAppContext.get(PaymentDao.class);
		NewsDao newsDao = WebAppContext.get(NewsDao.class);

		components.add(AuthenticationService.class, new AuthenticationServiceImpl(userDao))
				  .add(UserService.class, new UserServiceImpl(userDao))
				  .add(TariffService.class, new TariffServiceImpl(tariffDao))
				  .add(ServicesService.class, new ServicesServiceImpl(serviceDao))
				  .add(PaymentService.class, new PaymentServiceImpl(paymentDao, userDao))
				  .add(NewsService.class, new NewsServiceImpl(newsDao));

		log.info("Service components added");
	}

}
