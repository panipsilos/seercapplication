package org.providers.spreedly;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paymentservice.IOperations;
import org.paymentservice.Provider;
import org.paymentservice.datamodel.TransactionData;
import org.paymentservice.providers.spreedly.SpreedlyTransactionData;
import org.utilities.XmlFormatter;

public class SpreedlyRefundServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		SpreedlyOperations operations = new SpreedlyOperations();
		
		//get the form parameters
		String captureToken = req.getParameter("captureToken");
		String amount = req.getParameter("amount");
		
		
		
		//response variable
		String result;	
		
//		//call authorise method
//		if(amount.compareTo("")==0)
//		{
//			result = operations.refund(captureToken);
//		}
//		else
//		{
//			result = operations.refund(captureToken, amount);
//		}
//		
//		//print output
//		PrintWriter out = resp.getWriter();
//		out.println(new XmlFormatter().format(result.toString()));
		
		
		Provider provider = null;
		try {
			provider = new Provider(1,"J0QM5AkMDWyzV9NnvPtuNYhsU7Q","HjkPEYVxQ04U0FiHlwS5Cd17djh4JO8nE6X1Htju9koXo7qYw9Q1M6PdHJexXJDh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TransactionData td = new SpreedlyTransactionData().amount("300");
		IOperations operator = provider.getOperator();
		operator.refund(captureToken, td);
		System.out.print("finished task");
	}
}
