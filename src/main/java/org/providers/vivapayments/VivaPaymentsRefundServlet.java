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
import org.utilities.JsonFormatter;
//import org.json.JSONObject;

public class VivaPaymentsRefundServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		VivaPaymentsOperations vo = new VivaPaymentsOperations();
		
		//read parameteres from form
		String transactionId = req.getParameter("transactionId");
		String amount = req.getParameter("amount");
		
		//get the redirect URL "hardcoded + orderID"
		String result = vo.refund(transactionId, amount);
		
		JsonFormatter jf = new JsonFormatter();
		PrintWriter out = resp.getWriter();
		out.print(jf.parseJson(result));
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
	}

}
