/**
 * 
 */
package org.paymentservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.paymentservice.datatypes.RequestMethod;

/**
 * @author FGONIDIS
 * 
 */
public class Http {

	/**
	 * 
	 */
	private String username;
	private String password;

	/**
	 * Instantiate Http and store credentials
	 */
	public Http() {
		// TODO Auto-generated constructor stub
	}

	public Http(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@SuppressWarnings("deprecation")
	public String httpRequest(RequestMethod requestMethod, String url, Map<String, String> requestHeader,
			Map<String, String> requestData) {
		try {

			System.out.println("Sending HttpRequest");
			// Authenticate
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(url, AuthScope.ANY_PORT),
					new UsernamePasswordCredentials(this.username,
							this.password));

			// create Http client
			@SuppressWarnings("deprecation")
			HttpClient httpclient = new DefaultHttpClient();
			
			//Http response 
			HttpResponse response = null;
			
			// according to the noun (GET, POST, DELETE) create the respective			
			switch(requestMethod) {
				case GET:
				{
					HttpGet httpget = new HttpGet(url);
					// authenticate
					httpget.addHeader(BasicScheme.authenticate(
							new UsernamePasswordCredentials(username, password),
							"UTF-8", false));
					// add headers
					
					// send request
					response = httpclient.execute(httpget);
					break;
				}
				case POST:
				{
					HttpPost httppost = new HttpPost(url);
					
					// authenticate
					httppost.addHeader(BasicScheme.authenticate(
							new UsernamePasswordCredentials(username, password),
							"UTF-8", false));
					// add headers

					// add data
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					//check that request data not null. In case no data are sent (Spreedly refund) the params are empty
					if(requestData!=null)
					{
						for (Map.Entry<String, String> entry : requestData.entrySet()) {
							params.add(new BasicNameValuePair(entry.getKey(), entry
									.getValue()));
						}
					}

					httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					// send request
					response = httpclient.execute(httppost);
					break;
				}
				//Most likely it will not be used.
				case DELETE:
				{	
					HttpDelete httpdelete = new HttpDelete(url);
					// authenticate
					httpdelete.addHeader(BasicScheme.authenticate(
							new UsernamePasswordCredentials(username, password),
							"UTF-8", false));
					// add headers

					// add data
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					//check that request data not null. In case no data are sent (Spreedly refund) the params are empty
//					if(requestData!=null)
//					{
//						for (Map.Entry<String, String> entry : requestData.entrySet()) {
//							params.add(new BasicNameValuePair(entry.getKey(), entry
//									.getValue()));
//						}
//					}
//
//					httpdelete.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

					// send request
					response = httpclient.execute(httpdelete);
					break;
				}
				
			}
			// HttpRequest


			// receive response
			String result = this.receiveResponse(response);
			return result;
		} catch (Exception e) {
			System.out.print(e);
			return e.toString();
		}

	}

	// read response
	@SuppressWarnings("finally")
	public String receiveResponse(HttpResponse response)
			throws IllegalStateException, IOException {

		HttpEntity entity = response.getEntity();
		StringBuffer result = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						instream));
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				System.out.println("Http Response: " + result.toString());
				return result.toString();
			} finally {
				instream.close();
				// return "";
			}
		}
		return "";
	}

}
