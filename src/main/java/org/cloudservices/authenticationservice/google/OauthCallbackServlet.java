package org.cloudservices.authenticationservice.google;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.utilities.RequestMethod;
import org.utilities.Http;
import org.utilities.HttpNew;

public class OauthCallbackServlet extends HttpServlet{
	
	/**
	 * 
	 */
	
	HttpNew http;
	
	Map<String, String> requestData;
	
	String response;
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Hola POST</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println("</body>");
	    out.println("</html>");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();

	    //get the authorization code
	    String requestURI = req.getQueryString();;
	    String[] sub = requestURI.split("code=");
	    String authorizationCode = sub[1];
	    
	    //send a POST to get the access token
	    requestData = new HashMap<String, String>();
		
		requestData.put("code", authorizationCode);
		requestData.put("client_id", "433195534865");
		requestData.put("client_secret", "D9yv8uq1hVF80z27PWr6vSII");
		requestData.put("redirect_uri", "http://localhost:8090/PaymentServiceProviders/oauth2callback");
		requestData.put("grant_type", "authorization_code");
		
		http = new HttpNew("", "");
		response = http.httpRequest(RequestMethod.POST, "https://accounts.google.com/o/oauth2/token", null, requestData);
		
		
		
		
	    
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Hola GET</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println(authorizationCode);
	    out.println("</body>");
	    out.println("</html>");
	}

}
