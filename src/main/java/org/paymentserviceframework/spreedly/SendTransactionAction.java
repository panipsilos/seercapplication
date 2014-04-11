package org.paymentserviceframework.spreedly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.paymentservice.datatypes.RequestMethod;
import org.paymentservice.utils.Http;
import org.paymentserviceframework.actions.IAction;
import org.utilities.XmlFormatter;

public class SendTransactionAction implements IAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String url = "https://core.spreedly.com/v1/gateways/I7qNGNVHxDKPfiJgjgA1P2I2vAO/purchase.xml";

		// read query parameters appended in the url by Spreedly
		String requestURI = request.getRequestURI().toString();
		String queryString = request.getQueryString();
		String paymentMethodToken = queryString.substring(6);

		
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("transaction[amount]", request.getParameter("amount"));
		requestData.put("transaction[currency_code]", "USD");
		requestData.put("transaction[payment_method_token]", paymentMethodToken);

		Http http = new Http("J0QM5AkMDWyzV9NnvPtuNYhsU7Q","HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh");
		 
		String outcome = http.httpRequest(RequestMethod.POST,"https://core.spreedly.com/v1/gateways/SP8GkisOIdQNOyECXveyHjhuNSQ/purchase.xml",null, requestData);

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.print(outcome);
	
	}
}
