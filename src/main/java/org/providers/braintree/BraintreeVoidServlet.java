package org.providers.braintree;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONObject;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

public class BraintreeVoidServlet extends HttpServlet {

	private static BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX, "8g6rcnm8xnmyqb7p", "px3smkxtn79cfyx2",
			"df0a499650f1b2f054b568f10393048c");

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		BraintreeOperations bo = new BraintreeOperations();
		Result<Transaction> result;
		
		result = bo.voidOperation(req.getParameter("transactionId"));
		
		
		PrintWriter out = resp.getWriter();
		if( result.isSuccess())
		{
			out.print("The payment has been completed successfully"+"\n");
			out.print(result.getTarget().getId()+"\n");
			out.print(result.getTarget().getProcessorResponseText()+"\n");
			out.print(result.getTarget().getAvsErrorResponseCode()+"\n");
			out.print(result.getTarget().getGatewayRejectionReason()+"\n");
			out.print(result.getTarget().getAmount()+"\n");
			out.print(result.getTarget().getProcessorResponseCode() + "\n");
			out.print(result.getTarget().getStatus().toString() + "\n");
		}
		else 
		{
			out.print("The payment has failed"+"\n");
			out.print(result.getMessage()+"\n");
			
		}
		
		
	}
}
