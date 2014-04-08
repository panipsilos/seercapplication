package org.providers.spreedly;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SpreedlyOperations {

	public String authorise(String paymentMethodToken, String amount,
			String currencyCode)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/gateways/I7qNGNVHxDKPfiJgjgA1P2I2vAO/authorize.xml";

			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("transaction[amount]", amount));
			params.add(new BasicNameValuePair("transaction[currency_code]",
					currencyCode));
			params.add(new BasicNameValuePair(
					"transaction[payment_method_token]", paymentMethodToken));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}

					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}
	
	public String capture(String authorisationToken)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/transactions/"+authorisationToken+"/capture.xml";

			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.


			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}

					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}
	
	public String capture(String authorisationToken, String amount)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/transactions/"+authorisationToken+"/capture.xml";

			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("transaction[amount]",amount));
			
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			
			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}

					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}
	
	public String voidOperation(String authorisationToken)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/transactions/"+authorisationToken+"/void.xml";

			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.


			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}

					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}
	
	public String refund(String captureToken, String amount)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/transactions/"+captureToken+"/credit.xml";
			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("transaction[amount]",amount));
			
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			
			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}
	
	public String refund(String captureToken)
	{
		try {
			// url to make the call
			String endpoint = "https://core.spreedly.com/v1/transactions/"+captureToken+"/credit.xml";

			// create http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(endpoint);

			// add authentiction header
			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.


			// read the response
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			StringBuffer result = new StringBuffer();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					System.out.println(result.toString());
				} finally {
					instream.close();
				}
				return result.toString();
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");

		return "";
	}

}
