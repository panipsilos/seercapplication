package org.cloudservices.mailing.mailgun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.utilities.*;

public class MailgunServlet extends HttpServlet{

	/**
	 * 
	 */
	
	Map<String, String> requestData;
	
	HttpNew http;
	
	String response;
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
	    PrintWriter out = resp.getWriter();
	    
		System.out.println("got them");
		
		String response = this.sendEmail(req.getParameter("From"), req.getParameter("To"), req.getParameter("Subject"), req.getParameter("Text"));
		
		out.print(response);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("got them");
	
	}
	
	//uses the API to connect to MailGun
	public String sendEmail(String from, String to, String subject, String text) {

		requestData = new HashMap<String, String>();
		
		requestData.put("from", from);
		requestData.put("to", to);
		requestData.put("subject", subject);
		requestData.put("text", text);
		
		http = new HttpNew("api", "key-99757yh3icy8j3sq96wrmf-epa8v9e24");
		response = http.httpRequest(RequestMethod.POST, "https://api.mailgun.net/v2/sandboxe3eec0dbe1474b76a284a9da57e0dee1.mailgun.org/messages", null, requestData);
		
		return response ;
	}
	
	
	

}
