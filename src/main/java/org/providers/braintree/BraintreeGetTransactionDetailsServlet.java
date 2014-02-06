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
import com.braintreegateway.ValidationError;

public class BraintreeGetTransactionDetailsServlet extends HttpServlet {

	private static BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX, "8g6rcnm8xnmyqb7p", "px3smkxtn79cfyx2",
			"df0a499650f1b2f054b568f10393048c");

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		BraintreeOperations bo = new BraintreeOperations();
		Transaction transaction;

		transaction = bo.getTransactionDetials(req
				.getParameter("transactionId"));

		PrintWriter out = resp.getWriter();

		out.print("The payment has been captured successfully" + "\n");
		out.print(transaction.getId() + "\n");
		out.print(transaction.getProcessorResponseText() + "\n");
		out.print(transaction.getAvsErrorResponseCode() + "\n");
		out.print(transaction.getGatewayRejectionReason() + "\n");
		out.print(transaction.getAmount() + "\n");
		out.print(transaction.getProcessorResponseCode() + "\n");
		out.print(transaction.getStatus().toString() + "\n");

	}

}
