package org.providers.stripe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paymentservice.IOperations;
import org.paymentservice.Provider;
import org.paymentservice.datamodel.TransactionData;
import org.paymentservice.providers.stripe.StripeTransactionData;
import org.utilities.JsonFormatter;

public class StripeRefundServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
		String result;
		
		PrintWriter out = resp.getWriter();
		String captureToken = req.getParameter("captureToken");	
		String amount = req.getParameter("amount");	
		
		Provider provider = null;
		try {
			provider = new Provider(2,"sk_test_gcTm7NdWkkX0xJyRbd88IOgY","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TransactionData td = new StripeTransactionData().amount(amount);
		
		IOperations operations =  provider.getOperator();
		result = operations.refund(captureToken, td);
//		StripeOperations so = new StripeOperations();
//		if(amount.compareTo("")==0)
//		{
//			result = so.refund(captureToken);
//		}
//		else
//		{
//			result = so.refund(captureToken, amount);
//		}		
		out.print(JsonFormatter.parseJson(result));
		
	}	

}
