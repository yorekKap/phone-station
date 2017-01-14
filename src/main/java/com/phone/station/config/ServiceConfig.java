package com.phone.station.config;

import com.phone.station.dao.interfaces.MessageDao;
import com.phone.station.dao.interfaces.PaymentDao;
import com.phone.station.dao.interfaces.ServiceDao;
import com.phone.station.dao.interfaces.TariffDao;
import com.phone.station.dao.interfaces.UserDao;
import com.phone.station.service.impl.AuthenticationServiceImpl;
import com.phone.station.service.impl.MessageServiceImpl;
import com.phone.station.service.impl.PaymentServiceImpl;
import com.phone.station.service.impl.ServicesServiceImpl;
import com.phone.station.service.impl.TariffServiceImpl;
import com.phone.station.service.impl.UserServiceImpl;
import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.service.interfaces.MessageService;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.service.interfaces.UserService;

public class ServiceConfig implements Config {

	@Override
	public void init(Components components) {
		UserDao userDao = WebAppContext.get(UserDao.class);
		TariffDao tariffDao = WebAppContext.get(TariffDao.class);
		ServiceDao serviceDao = WebAppContext.get(ServiceDao.class);
		PaymentDao paymentDao = WebAppContext.get(PaymentDao.class);
		MessageDao messageDao = WebAppContext.get(MessageDao.class);

		components.add(AuthenticationService.class, new AuthenticationServiceImpl(userDao))
				  .add(UserService.class, new UserServiceImpl(userDao))
				  .add(TariffService.class, new TariffServiceImpl(tariffDao))
				  .add(ServicesService.class, new ServicesServiceImpl(serviceDao))
				  .add(PaymentService.class, new PaymentServiceImpl(paymentDao, userDao))
				  .add(MessageService.class, new MessageServiceImpl(messageDao));
	}

}
