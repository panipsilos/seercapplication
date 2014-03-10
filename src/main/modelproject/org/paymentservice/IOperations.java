package org.paymentservice;

import  org.paymentservice.datamodel.*;


public interface IOperations {

	/**
	 * 
	 */
	public String purchase(TransactionData transactionData);

	/**
	 * @param paymentMethod TODO
	 * @param amount TODO
	 * @param currency TODO
	 * 
	 */
	public String authorise(PaymentMethod paymentMethod, String amount, String currency);

	/**
	 * 
	 */
	public String capture(String authenticationToken, String amount);

	/**
	 * 
	 */
	 public String refund(String captureToken, TransactionData transactionData);

	/**
	 * 
	 */
	 java.lang.String voidOperation(String authorisationToken);

}
