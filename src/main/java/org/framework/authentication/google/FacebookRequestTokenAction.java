package org.framework.authentication.google;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.paymentserviceframework.actions.IAction;
import org.utilities.HttpClientUtilities;
import org.utilities.HttpNew;
import org.utilities.RequestMethod;

public class FacebookRequestTokenAction implements IAction{
	
	List<NameValuePair> queryParams = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 
		
		//parse URL and get "code"		
		String requestURI = request.getQueryString();
	    String[] sub = requestURI.split("code=");
	    String authentication_code = sub[1];
//		HttpClientUtilities httpClientUtilities = new HttpClientUtilities();
//		String authentication_code = httpClientUtilities.getParameters(requestURI, "code");
		System.out.println(authentication_code);
		
		//request access_token
		String url = "https://graph.facebook.com/oauth/access_token";
		
		//construct query params
	    Map<String,String> requestData = new HashMap<String, String>();
		
		requestData.put("client_id", "603302976432823");
		requestData.put("redirect_uri", "http://localhost:8090/PaymentServiceProviders/frontcontroller");
		requestData.put("client_secret", "e9fa3d2754be4f8fb8e87543c30b09b3");
		requestData.put("code", authentication_code);
		
		//send request, wait for token
		HttpNew http = new HttpNew();
		String fbResponse = http.httpRequest(RequestMethod.GET, url, null, requestData);
		
	}

}
