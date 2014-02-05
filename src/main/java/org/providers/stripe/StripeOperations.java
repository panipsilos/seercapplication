package org.providers.stripe;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public class StripeOperations {

	public String authorise(String token, String amount, String currency)

	{
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", currency);
		chargeParams.put("card", token); // obtained with Stripe.js
		chargeParams.put("description", "Charge for test@example.com");

		// authorise "capture" is false is hardcoded
		chargeParams.put("capture", false);

		String result;

		try {
			Charge charge = Charge.create(chargeParams);
			result = charge.toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public String purchase(String token, String amount, String currency)

	{
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", currency);
		chargeParams.put("card", token); // obtained with Stripe.js
		chargeParams.put("description", "Charge for test@example.com");

		// authorise "capture" is by deafult true

		String result;

		try {
			Charge charge = Charge.create(chargeParams);
			result = charge.toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public String capture(String authorisationToken) {
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";
		
		String result; 
		
		try {
			Charge ch = Charge.retrieve(authorisationToken);
			result = ch.capture().toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String capture(String authorisationToken, String amount) {
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";
		
		String result; 
		
		try {
			Charge ch = Charge.retrieve(authorisationToken);
			
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", amount);
			
			result = ch.capture(chargeParams).toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String refund(String captureToken, String amount) {
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";
		
		String result; 
		
		try {
			
			// id of the charge is required
			Charge ch = Charge.retrieve(captureToken);
			
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", amount);
			
			result = ch.refund(chargeParams).toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String refund(String captureToken) {
		Stripe.apiKey = "sk_test_gcTm7NdWkkX0xJyRbd88IOgY";
		
		String result; 
		
		try {
			
			//id of the charge is required
			Charge ch = Charge.retrieve(captureToken);
											
			result = ch.refund().toString();
		} catch (StripeException e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

}
