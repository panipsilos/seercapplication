package org.providers.vivapayments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
import org.utilities.JsonFormatter;

public class VivaPaymentsCaptureServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
								
		VivaPaymentsOperations vo = new VivaPaymentsOperations();
		
		String transactionId = req.getParameter("transactionId");
		String amount = req.getParameter("amount");
		
		
		String result = vo.capture(transactionId, amount);
		
		JsonFormatter jf = new JsonFormatter();
		
		PrintWriter out = resp.getWriter();
		out.println(jf.parseJson(result));		
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
	}

}
