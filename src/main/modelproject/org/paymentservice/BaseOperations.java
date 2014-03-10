/**
 * 
 */
package org.paymentservice;

import org.paymentservice.datamodel.PaymentMethod;
import org.paymentservice.datamodel.TransactionData;
import org.paymentservice.datatypes.Operation;
import org.paymentservice.datatypes.RequestMethod;
import org.paymentservice.utils.Http;

/**
 * @author FGONIDIS
 * 
 */
public class BaseOperations implements IOperations {

	private IOperationUtils operationUtils;
	/**
	 * reference to Http 		
	 */
	private Http http;
	/**
	 * response of the http response 		
	 */
	private String response;
	
	public Http getHttp(){
		return http;
	}
	
	/**
	 * DEfault Constructor
	 */
	public BaseOperations() {}
	/**
	 * 
	 */
	public BaseOperations(IOperationUtils operationUtils, Http http) {
		this.operationUtils = operationUtils;
		this.http = http;
	}

		
	public IOperationUtils getOperationUtils() {
		return operationUtils;
	}

	public void setOperationUtils(IOperationUtils operationUtils) {
		this.operationUtils = operationUtils;
	}

	public void setHttp(Http http) {
		this.http = http;
	}

	public String purchase(TransactionData transactionData) {
		//get endpoint
		String endpoint = operationUtils.buildEndpoint(Operation.PURCHASE, null);
		// send http request and receive response, set default Request Methods, specific providers may override these operation if they 
		//have alternative implementations.
		response = http.httpRequest(RequestMethod.POST,endpoint, null, transactionData.requestData);
		return response;
	}

	/**
	 * @param paymentMethod
	 *            TODO
	 * @param amount
	 *            TODO
	 * @param currency
	 *            TODO
	 * 
	 */
	public String authorise(PaymentMethod paymentMethod, String amount,
			String currency) {
		return null;
	}

	/**
	 * 
	 */
	public String capture(String authenticationToken, String amount) {
		return null;
	}

	/**
	 * Arguments are capture token and the transactionData. In the transactionData optionally goes the amount
	 */
	public String refund(String captureToken, TransactionData transactionData) {
		//get Endpoint
		String endpoint = operationUtils.buildEndpoint(Operation.REFUND, captureToken);
		// see http request and receive response
		response = http.httpRequest(RequestMethod.POST,endpoint, null, transactionData.requestData); //maybe change constructor of http request with build pattern
		return response;
	}

	/**
	 * 
	 */
	public String voidOperation(String authorisationToken) {
		return null;
	}
}
