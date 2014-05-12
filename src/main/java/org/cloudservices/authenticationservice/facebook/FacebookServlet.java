package org.cloudservices.authenticationservice.facebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.utilities.HttpNew;
import org.utilities.JsonFormatter;
import org.utilities.RequestMethod;

public class FacebookServlet extends HttpServlet{
	
	/**
	 * 
	 */
	
	HttpNew http;
	
	Map<String, String> requestData;
	
	String response;
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.sendRedirect("https://www.facebook.com/dialog/oauth?client_id=603302976432823&redirect_uri=http://127.0.0.1:8090/PaymentServiceProviders/facebook-response");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
	    PrintWriter out = resp.getWriter();

	    //get the authorization code
	    String requestURI = req.getQueryString();;
	    String[] sub = requestURI.split("code=");
	    String authorizationCode = sub[1];
	    
			
		//parse jSON and read access token 
//		String google_token = JsonFormatter.getJsonElement(response, "access_token");
//		String[] element =  google_token.split("\"");
//		String access_token = element[1];
	    
		String url = "https://graph.facebook.com/oauth/access_token";
		
		//construct query params
	    Map<String,String> requestData = new HashMap<String, String>();
		
		requestData.put("client_id", "603302976432823");
		requestData.put("redirect_uri", "http://localhost:8090/PaymentServiceProviders/facebook-response");
		requestData.put("client_secret", "e9fa3d2754be4f8fb8e87543c30b09b3");
		requestData.put("code", authorizationCode);
		
		//send request, wait for token
		http = new HttpNew();
		String fbResponse = http.httpRequest(RequestMethod.GET, url, null, requestData);
		
		//parse access_token
		String[] sub1 = fbResponse.split("&");
		String[] sub2 = sub1[0].split("=");
		String accessToken = sub2[1];
		
		
		//send request to get account details
		String data = http.httpRequest(RequestMethod.GET,"https://graph.facebook.com/me?access_token="+accessToken, null,null);
		
		String prettyResponse = JsonFormatter.parseJson(data);
	    
	    out.print(prettyResponse);
	}
}
