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
//import org.apache.tomcat.jdbc.pool.interceptor.QueryTimeoutInterceptor;
import org.utilities.XmlFormatter;

@SuppressWarnings("serial")
public class SpreedlyAuthoriseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		SpreedlyOperations operations = new SpreedlyOperations();
		
		//get the payment_method_token
		String queryString = req.getQueryString();
		String paymentMethodToken = queryString.substring(6);
		
		//hardcoded elemetns. up to user of the framework to fill in these values
		String amount = "100";
		String currencyCode = "EUR";
				
		//call authorise method
		String result = operations.authorise(paymentMethodToken, amount, currencyCode);
		
		//print output
		PrintWriter out = resp.getWriter();
		out.println(new XmlFormatter().format(result.toString()));

	}
}