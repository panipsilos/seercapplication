package org.cloudservices.authenticationservice.facebook;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacebookServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.sendRedirect("https://www.facebook.com/dialog/oauth?client_id=603302976432823&redirect_uri=http://127.0.0.1:8090/PaymentServiceProviders/facebook-response");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("response received");
	}
}
