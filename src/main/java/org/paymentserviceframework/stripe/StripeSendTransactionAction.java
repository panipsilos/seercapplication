package org.paymentserviceframework.stripe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.stripe.*;
import com.stripe.model.Charge;
import com.stripe.exception.StripeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paymentserviceframework.actions.IAction;

public class StripeSendTransactionAction implements IAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/plain");

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// read from form
		String token = request.getParameter("stripeToken");
		
		// read from session. FillOutForm has put it there
		String amount = (String) request.getSession().getAttribute("amount");
		
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY"; 

		// construct the payment call using stripe library
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", "usd");
		chargeParams.put("card", token); // obtained with Stripe.js
		chargeParams.put("description", "Charge for test@example.com");
		try {
			Charge charge = Charge.create(chargeParams);
			System.out.println(charge);

			// print result on the browser
			out.print(charge);
		} catch (StripeException e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}

	}

}
