package org.paymentserviceframework.spreedly;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
import org.paymentservice.datatypes.RequestMethod;
import org.paymentservice.utils.Http;
import org.paymentserviceframework.actions.IAction;
import org.utilities.XmlFormatter;

import com.sun.faces.util.HtmlUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class FillOutFormAction implements IAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			// read "ampount" and store in the servletContext
			request.setAttribute("amount", request.getParameter("amount"));
			request.getSession().setAttribute("amount", request.getParameter("amount"));
			
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    // 1. Configure FreeMarker
		    //
		    // You should do this ONLY ONCE, when your application starts,
		    // then reuse the same Configuration object elsewhere.
		    
		    Configuration cfg = new Configuration();
		    
		    // Where do we load the templates from:
		    try {
				cfg.setDirectoryForTemplateLoading(new File("C:\\"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    // Some other recommended settings:
		    cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		    cfg.setDefaultEncoding("UTF-8");
//		    /cfg.setLocale(Locale.En);
		    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		    // 2. Proccess template(s)
		    //
		    // You will do this for several times in typical applications.
		    
		    // 2.1. Prepare the template input:
		    
		    Map<String, Object> input = new HashMap<String, Object>();
		    
//		 /   input.put("title", "Vogella example");
		    
		    // 2.2. Get the template

		    Template template = null;
			try {
				template = cfg.getTemplate("helloworld.ftl");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
		    // 2.3. Generate the output

		    // Write output to the console
		  
		    try {
				template.process(input, out);
			} catch (TemplateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			response.setContentType("text/html");
			
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Hola</title>");
		    out.println("</head>");
		    out.println("<body bgcolor=\"white\">");
		    out.println("<h1>");
		    String amount = request.getParameter("amount");
		    out.println(amount);
		    out.println("</h1>");
		    out.println("</body>");
		    out.println("</html>");
		*/
			System.out.println("html printed");
	
	}
}
