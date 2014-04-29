package org.cloudservices.authenticationservice.facebook;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacebookResponse {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("response received");
		//resp.sendRedirect("https://www.facebook.com/dialog/oauth?client_id=603302976432823&redirect_uri=/PaymentServiceProviders/facebook-response");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("response received");
	}

}
