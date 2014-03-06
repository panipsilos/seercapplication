package org.providers.braintree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

@ManagedBean(name= "braintree") 
public class BraintreeOperations {

	private static BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX, "8g6rcnm8xnmyqb7p", "px3smkxtn79cfyx2",
			"df0a499650f1b2f054b568f10393048c");
	
	
	
	
	public String getUrl()
	{
		return gateway.transparentRedirect().url();
	}
	
	public String setTrData()
	{
		TransactionRequest trParams = new TransactionRequest()
	    .type(Transaction.Type.SALE)
	    .amount(new BigDecimal("10.00"));
		String trdata = gateway.transparentRedirect().trData(trParams, "http://127.0.0.1:8090/PaymentServiceProviders/BraintreePurchaseServlet");
		return  trdata;
	}


	public Result<Transaction> authorise(String cardNumber, String cvv,
			String month, String year, String amount, String currencyCode) {
		
		TransactionRequest transactionRequest = new TransactionRequest()
				.amount(new BigDecimal("1000.00")).creditCard()
				.number(cardNumber).cvv(cvv).expirationMonth(month)
				.expirationYear(year).done().options()
				.submitForSettlement(false).done();

		Result<Transaction> result = gateway.transaction().sale(
				transactionRequest);
		return result;
	}

	public Result<Transaction> purchase(String cardNumber, String cvv,
			String month, String year, String amount, String currencyCode) {
		
		TransactionRequest transactionRequest = new TransactionRequest()
				.amount(new BigDecimal("1000.00")).creditCard()
				.number(cardNumber).cvv(cvv).expirationMonth(month)
				.expirationYear(year).done().options()
				.submitForSettlement(true).done();

		Result<Transaction> result = gateway.transaction().sale(
				transactionRequest);
		return result;
	}

	public Result<Transaction> capture(String transactionId, String amount) {
		
		Result<Transaction> result = gateway.transaction().submitForSettlement(transactionId, new BigDecimal(amount));
		return result;
	}
	
	public Result<Transaction> capture(String transactionId) {
		
		Result<Transaction> result = gateway.transaction().submitForSettlement(transactionId);
		return result;
	}
	
	public Result<Transaction> refund(String transactionId) {
		
		Result<Transaction> result = gateway.transaction().refund(transactionId);
		return result;
	}
	
	public Result<Transaction> refund(String transactionId, String amount) {
		
		Result<Transaction> result = gateway.transaction().refund(transactionId, new BigDecimal(amount));
		return result;
	}
	
	public Transaction getTransactionDetials(String transactionId) {
		
		Transaction transaction = gateway.transaction().find(transactionId);
		return transaction;
	}
	
	public Result<Transaction> voidOperation(String transactionId) {
		
		Result<Transaction> result = gateway.transaction().voidTransaction(transactionId);
		return result;
	}
	

	

	

}
