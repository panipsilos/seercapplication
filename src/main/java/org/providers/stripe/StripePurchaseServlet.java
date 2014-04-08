package org.providers.stripe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paymentservice.IOperations;
import org.paymentservice.Provider;
import org.paymentservice.datamodel.TransactionData;
import org.paymentservice.providers.stripe.StripeTransactionData;

import com.stripe.*;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@SuppressWarnings("serial")
public class StripePurchaseServlet extends HttpServlet {

	
	int counter = 0;
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
		String result;
		
		PrintWriter out = resp.getWriter();
		String token = req.getParameter("stripeToken");		
		
		// move to the payment service library
		TransactionData td = new StripeTransactionData().amount("100").currencyCode("EUR").paymentMethod().token(token).done();
		Provider provider = null;
		try {
			provider = new Provider(2, "sk_test_gcTm7NdWkkX0xJyRbd88IOgY",null);
		} catch (Exception e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IOperations operations  = provider.getOperator();
		result = operations.purchase(td);
		
//		StripeOperations so = new StripeOperations();
//		result = so.purchase(token, "100", "USD");
//		out.print(result);
	}	
}
