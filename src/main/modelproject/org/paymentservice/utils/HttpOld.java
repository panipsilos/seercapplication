/**
 * 
 */
package org.paymentservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author FGONIDIS
 * 
 */
public class HttpOld {

	private String apiVersion; // What is this? Maybe for the url
	private String username;
	private String password;
	/**
	 * 
	 */
	public HttpOld() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Store the credentials
	 */
	public HttpOld(String username, String password){
		this.username = username;
		this.password = password;
	}
	/**
	 * gets the URL Connection, fills in the data ( if any),sends the request,
	 * return the response
	 */
	@SuppressWarnings("deprecation")
	java.lang.String httpRequest(String url, Map<String, String> requestHeader,Map<String, String> requestData) {
		try {

			// Authenticate
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(url,AuthScope.ANY_PORT), new UsernamePasswordCredentials(username,password));

			// create Http client
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			
			// add headers
			

			// add data
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : requestData.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			// send request
			HttpResponse response = httpclient.execute(httppost);

			// receive response
			String result = this.receiveResponse(response);
			return result;
		} catch (Exception e) {
			System.out.print(e);
			return e.toString();
		}

	}

	public static String inputStreamToString(InputStream inputStream)
			throws IOException {
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[0x1000];
		int bytesRead = inputReader.read(buffer, 0, buffer.length);
		while (bytesRead >= 0) {
			builder.append(buffer, 0, bytesRead);
			bytesRead = inputReader.read(buffer, 0, buffer.length);
		}
		return builder.toString();
	}

	// read response
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

				System.out.println(result.toString());
				return result.toString();
			} finally {
				instream.close();
				return "";
			}

		}
		return "";
	}
}
