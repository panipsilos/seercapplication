package org.providers.stripe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.*;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@SuppressWarnings("serial")
public class StripeAuthoriseServlet extends HttpServlet {

	
	int counter = 0;
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
	        }
	
	
	// doGET is inoked
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
		String result;
		
		PrintWriter out = resp.getWriter();
		String token = req.getParameter("stripeToken");		
		
		StripeOperations so = new StripeOperations();
		result = so.authorise(token, "200", "USD");
		out.print(result);
		
	}	
}
