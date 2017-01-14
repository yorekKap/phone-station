package com.phone.station.web.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.phone.station.config.WebAppContext;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
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
		Controller controller =  helper.getController(request);
		String page = controller.execute(request, response);

		try {
			if(page != null){
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
