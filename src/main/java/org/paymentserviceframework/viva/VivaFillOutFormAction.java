package org.paymentserviceframework.viva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.paymentserviceframework.actions.IAction;

public class VivaFillOutFormAction implements IAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
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
				params.add(new BasicNameValuePair("Amount", request.getParameter("amount")));

				try {
					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Execute and get the response.
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httppost);
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpEntity entity = httpResponse.getEntity();
				StringBuffer res = new StringBuffer();
				if (entity != null) {
					InputStream instream = null;
					try {
						instream = entity.getContent();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						BufferedReader rd = new BufferedReader(new InputStreamReader(
								instream));

						String line = "";
						try {
							while ((line = rd.readLine()) != null) {
								res.append(line);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println(res.toString());
					} finally {
						try {
							instream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// extract the order ID
				String orderId = res.substring(13, 25);
				String redirectUrl;

				// redirect user to the viva page appending the order id
				redirectUrl = "http://demo.vivapayments.com//web/newtransaction.aspx?ref="
						+ orderId;
				try {
					response.sendRedirect(redirectUrl);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

}
