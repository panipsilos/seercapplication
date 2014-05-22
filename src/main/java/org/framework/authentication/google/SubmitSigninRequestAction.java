package org.framework.authentication.google;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.paymentserviceframework.actions.IAction;
import org.utilities.HttpNew;
import org.utilities.RequestMethod;

/*
 * This is a FrontAction. Therefore requires servlet communication.  
 * It should inherit the FrontAction class so that it gets the request/response comm
 */
public class SubmitSigninRequestAction implements IAction {
	
	
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) {		
		//receive sign in request
		
		//submit sign in request (BA)
		
		String BAresponse = this.submitRequest();
		
		
		//redirect the client.
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(BAresponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// This should be a Back Action. 
	public String submitRequest()
	{
		org.utilities.HttpNew httpNew = new HttpNew();
		Map<String, String> requestData = new HashMap<String, String>();
		
		
		//values derived from service description, Ideally it will be a for loop which will receive the values from the service description 
		//and place them in the requestData.
		
		requestData.put("scope","email%20profile");  // Static Provider specific
		requestData.put("state","/profile"); // Static Provider specific
		requestData.put("response_type", "code"); // Static Provider specific
		requestData.put("redirect_uri","http://localhost:8090/PaymentServiceProviders/frontcontroller"); // Static User specific
		requestData.put("client_id","433195534865.apps.googleusercontent.com"); // // Static User specific
		requestData.put("approval_prompt","force"); // Static Provider specific
		
		// Static Provider specific, Static Provider specific
		String response = httpNew.httpRequest(RequestMethod.GET, "https://accounts.google.com/o/oauth2/auth", null, requestData);
		System.out.println(response);
		return response;
	}



}
