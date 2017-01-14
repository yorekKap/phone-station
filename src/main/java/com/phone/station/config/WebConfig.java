package com.phone.station.config;


import java.util.ResourceBundle.Control;

import org.apache.catalina.connector.Request;
import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.phone.station.service.interfaces.AuthenticationService;
import com.phone.station.service.interfaces.PaymentService;
import com.phone.station.service.interfaces.ServicesService;
import com.phone.station.service.interfaces.TariffService;
import com.phone.station.service.interfaces.UserService;
import com.phone.station.web.controllers.AdminController;
import com.phone.station.web.controllers.AdminServicesController;
import com.phone.station.web.controllers.AdminTariffsController;
import com.phone.station.web.controllers.AdminUsersController;
import com.phone.station.web.controllers.HomeController;
import com.phone.station.web.controllers.LoginController;
import com.phone.station.web.controllers.LogoutController;
import com.phone.station.web.controllers.PaymentsController;
import com.phone.station.web.controllers.RefillController;
import com.phone.station.web.controllers.RegisterController;
import com.phone.station.web.controllers.ServiceController;
import com.phone.station.web.controllers.TariffDisconnectController;
import com.phone.station.web.controllers.TariffsController;
import com.phone.station.web.controllers.WelcomeController;
import com.phone.station.web.dispatcher.ControllersMapper;
import com.phone.station.web.dispatcher.RequestHelper;
import com.phone.station.web.dispatcher.ViewResolver;

public class WebConfig implements Config {

	@Override
	public void init(Components components){
		components.add(ControllersMapper.class, getControllersMapper())
				  .add(ViewResolver.class, getViewResolver())
				  .add(RequestHelper.class, getRequestHelper(getControllersMapper()));
	}

	public ControllersMapper getControllersMapper(){
		ControllersMapper controllersMapper = new ControllersMapper();

		AuthenticationService authService = WebAppContext.get(AuthenticationService.class);
		UserService userService = WebAppContext.get(UserService.class);
		TariffService tariffService = WebAppContext.get(TariffService.class);
		ServicesService servicesService = WebAppContext.get(ServicesService.class);
		PaymentService paymentService = WebAppContext.get(PaymentService.class);

		controllersMapper.add("/", 			        new WelcomeController())
						 .add("/login", 	        new LoginController(authService))
						 .add("/register",          new RegisterController(userService))
						 .add("/home", 		        new HomeController(userService))
						 .add("/logout",        	new LogoutController())
						 .add("/tariffs",  		    new TariffsController(tariffService, userService))
						 .add("/tariff-disconnect", new TariffDisconnectController(userService))
						 .add("/services",          new ServiceController(servicesService, userService,
								 										  paymentService))
						 .add("/payments",		    new PaymentsController(paymentService))
						 .add("/refill",            new RefillController(paymentService))
						 .add("/admin",          	new AdminController(userService))
						 .add("/admin/users",       new AdminUsersController(userService))
						 .add("/admin/services",    new AdminServicesController(servicesService))
						 .add("/admin/tariffs",     new AdminTariffsController(tariffService));


		return controllersMapper;
	}

	public ViewResolver getViewResolver(){
		return ViewResolver.getBuilder()
										.setPrefix("/WEB-INF/views/")
										.setSuffix(".jsp")
										.create();
	}

	public RequestHelper getRequestHelper(ControllersMapper mapper){
		return new RequestHelper(mapper);
	}

}
