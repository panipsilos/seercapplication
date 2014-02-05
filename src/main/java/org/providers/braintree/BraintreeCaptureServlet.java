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

public class BraintreeCaptureServlet extends HttpServlet{
	
	
	private static BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX, "8g6rcnm8xnmyqb7p", "px3smkxtn79cfyx2", "df0a499650f1b2f054b568f10393048c");
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		String number = req.getParameter("number");
		
		TransactionRequest transactionRequest = new TransactionRequest()
        .amount(new BigDecimal("1000.00"))
        .creditCard()
            .number(req.getParameter("number"))
            .cvv(req.getParameter("cvv"))
            .expirationMonth(req.getParameter("month"))
            .expirationYear(req.getParameter("year"))
            .done()
        .options()
            .submitForSettlement(true)
            .done();
		
		Result<Transaction> result = gateway.transaction().sale(transactionRequest);
		
		
		PrintWriter out = resp.getWriter();
		if( result.isSuccess())
		{
			out.print("The payment has been completed successfully"+"\n");
			out.print(result.getTarget().getId()+"\n");
			out.print(result.getTarget().getProcessorResponseText()+"\n");
			out.print(result.getTarget().getAvsErrorResponseCode()+"\n");
			out.print(result.getTarget().getGatewayRejectionReason()+"\n");
			out.print(result.getTarget().getAmount()+"\n");
		}
		else 
		{
			out.print("The payment has failed"+"\n");
			out.print(result.getMessage()+"\n");
			
		}
		
		
	}
	
}
