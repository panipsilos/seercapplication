package org.cloudservices.authenticationservice.google;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.primefaces.json.JSONObject;
import org.utilities.JsonFormatter;
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


	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
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
		//receive access token 
		response = http.httpRequest(RequestMethod.POST, "https://accounts.google.com/o/oauth2/token", null, requestData);
		
		
		//parse jSON and read access token 
		String google_token = JsonFormatter.getJsonElement(response, "access_token");
		String[] element =  google_token.split("\"");
		String access_token = element[1];
		
		http = new HttpNew();
		String contacts = http.httpRequest(RequestMethod.GET, "https://www.googleapis.com/plus/v1/people/me?access_token="+access_token, null, null);
		String prettyResponse = JsonFormatter.parseJson(contacts);
	    
	    out.print(prettyResponse);

	}

}
