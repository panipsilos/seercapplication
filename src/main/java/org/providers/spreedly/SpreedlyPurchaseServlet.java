package org.providers.spreedly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.jdbc.pool.interceptor.QueryTimeoutInterceptor;
import org.utilities.XmlFormatter;

@SuppressWarnings("serial")
public class SpreedlyPurchaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {

			String url = "https://core.spreedly.com/v1/gateways/SP8GkisOIdQNOyECXveyHjhuNSQ/authorize.xml";

			// read query parameters appended in the url by Spreedly
			String requestURI = req.getRequestURI().toString();
			String queryString = req.getQueryString();
			String paymentMethodToken = queryString.substring(6);
			
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httppost = new HttpPost(url);

			httppost.addHeader(BasicScheme
					.authenticate(
							new UsernamePasswordCredentials(
									"J0QM5AkMDWyzV9NnvPtuNYhsU7Q",
									"HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh"),
							"UTF-8", false));

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("transaction[amount]", "500"));
			params.add(new BasicNameValuePair("transaction[currency_code]",
					"USD"));
			params.add(new BasicNameValuePair(
					"transaction[payment_method_token]",
					paymentMethodToken));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			// //add gateway token
			// String url =
			// "https://core.spreedly.com/v1/gateways/8pEwrvZGtr9IhhY3szc9g1KBsa1/purchase.xml";
			// //String url = "http://google.com";
			//
			// URL obj = new URL(url);
			// HttpURLConnection conn = (HttpURLConnection)
			// obj.openConnection();
			//
			// conn.setRequestProperty("Content-Type", "application/xml");
			// conn.setDoOutput(true);
			//
			// conn.setRequestMethod("GET");
			//
			//
			// // enironment key + personal access key
			// String userpass = "J0QM5AkMDWyzV9NnvPtuNYhsU7Q" + ":" +
			// "HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh";
			// String basicAuth = "Basic" +
			// javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			// conn.setRequestProperty ("Authorization", basicAuth);
			//
			// String data =
			// "<transaction><amount>100</amount><currency_code>USD</currency_code><payment_method_token>"+req.getParameter("token")+"</payment_method_token></transaction>";
			// OutputStreamWriter out = new
			// OutputStreamWriter(conn.getOutputStream());
			// out.write(data);
			// out.flush();

			// Get the response
			//
			// Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			PrintWriter out = resp.getWriter(); 
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(instream));
					StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					out.print(new XmlFormatter().format(result.toString()));					
					System.out.println(new XmlFormatter().format(result.toString()));
				} finally {
					instream.close();
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		System.out.print("");
	}

}