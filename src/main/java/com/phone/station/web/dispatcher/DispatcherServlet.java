package com.phone.station.web.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.phone.station.config.WebAppContext;
import com.phone.station.exceptions.dispatcher.BadRequestException;

/**
 * {@link HttpServlet} implementation for dispatching incoming
 * request to mapped controller
 *
 * @author yuri
 */
public class DispatcherServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(DispatcherServlet.class);

	private static final long serialVersionUID = 1L;

	RequestHelper helper;

    public DispatcherServlet() {
        super();
        helper = WebAppContext.get(RequestHelper.class);
   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    													throws ServletException, IOException {
    	processRequest(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response){
		try {
			Controller controller =  helper.getController(request);
			String page = controller.execute(request, response);

			if(page != null){
				request.getRequestDispatcher(page).forward(request, response);
			}

		} catch (ServletException e) {
			log.error("ServletException in DispatcherServlet : ", e);
		} catch (IOException e) {
			log.error("IOException in DispatcherServlet : ", e);
		} catch(BadRequestException e){
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				log.warn("404 error code is returned");
			} catch (IOException e1) {
				log.error("IOException in DispatcherServlet : ", e);
			}
		}

	}

}
