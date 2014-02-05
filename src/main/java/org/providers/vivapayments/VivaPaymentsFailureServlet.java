package org.providers.vivapayments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.utilities.JsonFormatter;

public class VivaPaymentsFailureServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		//response from viva payments
		String requestURI = req.getRequestURI().toString();
		String queryString = req.getQueryString();
		
		//get order code
		String orderCode = queryString.substring(2, 14);
		
		//ask for transaction details
		VivaPaymentsOperations vo = new VivaPaymentsOperations();
		String details = vo.getTransactionDetails(orderCode);
		
		//fromat json string
		JsonFormatter jf = new JsonFormatter();
		String prettyJson = jf.parseJson(details);
				
		//print result
		PrintWriter out = resp.getWriter();
		out.println("Payment has failed");
		out.print(prettyJson);

		
	}
}
