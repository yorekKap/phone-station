package com.phone.station.config;

import org.apache.log4j.Logger;

import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.service.interfaces.NewsService;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.controllers.admin.AdminController;
import com.phone.station.web.controllers.admin.AdminServicesController;
import com.phone.station.web.controllers.admin.AdminTariffsController;
import com.phone.station.web.controllers.admin.AdminUsersController;
import com.phone.station.web.controllers.authorization.LoginController;
import com.phone.station.web.controllers.authorization.LogoutController;
import com.phone.station.web.controllers.authorization.RegisterController;
import com.phone.station.web.controllers.user.HomeController;
import com.phone.station.web.controllers.user.NewsController;
import com.phone.station.web.controllers.user.PaymentsController;
import com.phone.station.web.controllers.user.RefillController;
import com.phone.station.web.controllers.user.ServiceController;
import com.phone.station.web.controllers.user.TariffsController;
import com.phone.station.web.dispatcher.ControllersMapper;
import com.phone.station.web.resolvers.ViewResolver;

/**
 * {@link Config} implementation for fulfilling components context
 * with web components
 *
 * @author yuri
 *
 */
public class WebConfig implements Config {
	private final static Logger log = Logger.getLogger(WebConfig.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		components.add(ControllersMapper.class, getControllersMapper())
				  .add(ViewResolver.class, getViewResolver());
	}

	/**
	 * @return {@link ControllersMapper} instance
	 */
	public ControllersMapper getControllersMapper() {
		AuthenticationService authService = WebAppContext.get(AuthenticationService.class);
		UserService userService = WebAppContext.get(UserService.class);
		TariffService tariffService = WebAppContext.get(TariffService.class);
		ServicesService servicesService = WebAppContext.get(ServicesService.class);
		PaymentService paymentService = WebAppContext.get(PaymentService.class);
		NewsService newsService = WebAppContext.get(NewsService.class);

		ControllersMapper controllersMapper = ControllersMapper.createBuilder()
				.add("/login",			   new LoginController(authService))
				.add("/register", 	       new RegisterController(userService))
				.add("/home", 			   new HomeController(userService))
				.add("/logout", 		   new LogoutController())
				.add("/news", 			   new NewsController(newsService))
				.add("/tariffs", 		   new TariffsController(tariffService, userService))
				.add("/services", 		   new ServiceController(servicesService, userService, paymentService))
				.add("/payments", 		   new PaymentsController(paymentService))
				.add("/refill",			   new RefillController(paymentService))
				.add("/admin", 			   new AdminController(userService, newsService))
				.add("/admin/users", 	   new AdminUsersController(userService))
				.add("/admin/services",    new AdminServicesController(servicesService))
				.add("/admin/tariffs", 	   new AdminTariffsController(tariffService))
				.build();

		log.info("ControllesMapper initialized");

		return controllersMapper;
	}

	/**
	 * @return {@link ViewResolver}
	 */
	public ViewResolver getViewResolver() {
		return ViewResolver.getBuilder().setPrefix("/WEB-INF/views/").setSuffix(".jsp").create();
	}


	//commented to show singleton usage
	/**
	 * @param mapper to link it with {@link ConrollersMapper}
	 * @return @{link RequestHelper}
	 *//*
	public RequestHelper getRequestHelper(ControllersMapper mapper) {
		return new RequestHelper(mapper);
	}*/

}
