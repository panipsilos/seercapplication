package org.providers.spreedly;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.utilities.XmlFormatter;

public class SpreedlyVoidServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		SpreedlyOperations operations = new SpreedlyOperations();
		
		//get the form parameters
		String authorisationToken = req.getParameter("authorisationToken");
		
		
		//response variable
		String result;	
		
		//call void method
		result = operations.voidOperation(authorisationToken);

		
		//print output
		PrintWriter out = resp.getWriter();
		out.println(new XmlFormatter().format(result.toString()));

	}
}
