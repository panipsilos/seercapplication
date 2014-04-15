package org.paymentserviceframework.braintree;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paymentserviceframework.actions.IAction;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

public class BraintreeSendTransactionAction implements IAction{
	
	private static BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX, "8g6rcnm8xnmyqb7p", "px3smkxtn79cfyx2",
			"df0a499650f1b2f054b568f10393048c");
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				
		
			// Fill in the request
			TransactionRequest transactionRequest = new TransactionRequest()
					.amount(new BigDecimal((String)request.getSession().getAttribute("amount"))).creditCard()
					.number(request.getParameter("number")).cvv(request.getParameter("cvv")).expirationMonth(request.getParameter("month"))
					.expirationYear(request.getParameter("year")).done().options()
					.submitForSettlement(true).done();

			// Submit request and get result
			Result<Transaction> result = gateway.transaction().sale(
					transactionRequest);
			
			// Print result
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result.isSuccess()) {
				out.print("The payment has been completed successfully" + "\n");
				out.print(result.getTarget().getId() + "\n");
				out.print(result.getTarget().getProcessorResponseText() + "\n");
				out.print(result.getTarget().getAvsErrorResponseCode() + "\n");
				out.print(result.getTarget().getGatewayRejectionReason() + "\n");
				out.print(result.getTarget().getAmount() + "\n");
				out.print(result.getTarget().getProcessorResponseCode() + "\n");
				out.print(result.getTarget().getStatus().toString() + "\n");
			} else {
				out.print("The payment has failed" + "\n");
				out.print(result.getMessage() + "\n");
				for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
			        System.out.println(error.getMessage());
				}
		}		
	}
}
