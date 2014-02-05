package org.providers.stripe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StripeRefundServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
		String result;
		
		PrintWriter out = resp.getWriter();
		String captureToken = req.getParameter("captureToken");	
		String amount = req.getParameter("amount");	
		
		StripeOperations so = new StripeOperations();
		if(amount.compareTo("")==0)
		{
			result = so.refund(captureToken);
		}
		else
		{
			result = so.refund(captureToken, amount);
		}
		out.print(result);
		
	}	

}
