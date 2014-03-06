package org.providers.vivapayments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public class VivaPaymentsOperations {

	public String authorise(String amount) throws ClientProtocolException,
			IOException

	{
		//endpoint for Viva Payments
		String url = "http://demo.vivapayments.com/api/orders/";

		//Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		// Authentication: Merchant ID : Password
		httppost.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("Amount", amount));
		params.add(new BasicNameValuePair("IsPreAuth", "true"));

		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}

		// extract the order ID
		String orderId = res.substring(13, 25);
		String result;

		// CHECK IF REDIRECT URL CAN BE SET PROGRAMMATICALLY
		// "https://github.com/VivaPayments/API/blob/master/Python/vivapayments.py"
		// redirect user to the viva page appending the order id
		result = "http://demo.vivapayments.com//web/newtransaction.aspx?ref="
				+ orderId;
		return result;
	}

	public String purchase(String amount) throws IOException

	{
		// endpoint for Viva Payments
		String url = "http://demo.vivapayments.com/api/orders";

		// Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		// Authentication: Merchant ID : Password
		httppost.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("Amount", amount));

		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}

		// extract the order ID
		String orderId = res.substring(13, 25);
		String result;

		// redirect user to the viva page appending the order id
		result = "http://demo.vivapayments.com//web/newtransaction.aspx?ref="
				+ orderId;
		return result;
	}

	public String getTransactionDetails(String orderCode) throws ClientProtocolException, IOException {
		// endpoint for Viva Payments
		String url = "http://demo.vivapayments.com/api/transactions/?ordercode="+orderCode;

		// Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		// Authentication: Merchant ID : Password
		httpget.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
		//List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		//params.add(new BasicNameValuePair("ordercode", orderCode));
		// /params.add(new BasicNameValuePair("IsPreAuth", "true"));

		//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}
		//return the details of the transactions
		return res.toString();
	}

	public String capture(String transactionId) throws ClientProtocolException, IOException {

		String url = "http://demo.vivapayments.com/api/transactions/"+transactionId;

		// Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		// Authentication: Merchant ID : Password
		httppost.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
	//	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	//  add(new BasicNameValuePair("Amount", amount));
		// /params.add(new BasicNameValuePair("IsPreAuth", "true"));

		//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}
		//return the details of the transactions
		return res.toString();
	}

	public String capture(String transactionId, String amount) throws ClientProtocolException, IOException {

		String url = "http://demo.vivapayments.com/api/transactions/"+transactionId;

		// Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		// Authentication: Merchant ID : Password
		httppost.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("Amount", amount));
		// /params.add(new BasicNameValuePair("IsPreAuth", "true"));

		//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}
		//return the details of the transactions
		return res.toString();
	}

	public String refund(String transactionId, String amount) throws ClientProtocolException, IOException {

		String url = "http://demo.vivapayments.com/api/transactions/"+transactionId;

		// Initialise HTTP request
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpdelete = new HttpDelete(url);

		// Authentication: Merchant ID : Password
		httpdelete.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(
						"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
				"UTF-8", false));

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("Amount", amount));
		// /params.add(new BasicNameValuePair("IsPreAuth", "true"));

		//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httpdelete);
		HttpEntity entity = response.getEntity();
		StringBuffer res = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));

				String line = "";
				while ((line = rd.readLine()) != null) {
					res.append(line);
				}

				System.out.println(res.toString());
			} finally {
				instream.close();
			}
		}
		//return the details of the transactions
		return res.toString();
	
	}

	public String refund(String captureToken) {

		String result = null;
		return result;
	}
	
	public String voidOperation (String orderCode) throws ClientProtocolException, IOException
	{
		// endpoint for Viva Payments
				String url = "http://demo.vivapayments.com/api/orders/"+orderCode;

				// Initialise HTTP request
				HttpClient httpclient = new DefaultHttpClient();
				HttpDelete httpdelete = new HttpDelete(url);

				// Authentication: Merchant ID : Password
				httpdelete.addHeader(BasicScheme.authenticate(
						new UsernamePasswordCredentials(
								"526117a4-f9ab-4abf-9615-caf2266cae0d", "123456"),
						"UTF-8", false));

				// Request parameters and other properties.
				List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("ordercode", orderCode));
				

				//httpdelete.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

				// Execute and get the response.
				HttpResponse response = httpclient.execute(httpdelete);
				HttpEntity entity = response.getEntity();
				StringBuffer res = new StringBuffer();
				if (entity != null) {
					InputStream instream = entity.getContent();
					try {
						BufferedReader rd = new BufferedReader(new InputStreamReader(
								instream));

						String line = "";
						while ((line = rd.readLine()) != null) {
							res.append(line);
						}

						System.out.println(res.toString());
					} finally {
						instream.close();
					}
				}
				//return the details of the transactions
				return res.toString();
	}

}
