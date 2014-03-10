/**
 * 
 */
package org.paymentservice.datamodel;

import java.util.HashMap;


/**
 * @author FGONIDIS
 * 
 */
public abstract class TransactionData {


	public PaymentMethod paymentMethod;
	/**
	 * Amount is declared here but used in the sub-classes
	 */
	private java.lang.String amount;
	/**
	 * Currency Code  is declared here but used in the sub-classes
	 */
	private java.lang.String currencyCode;
	/**
	 * Holds the key-value pairs to be sent along the http request
	 */
	public TransactionData() {
		this.requestData = new HashMap<String, String>();
	}
	public java.util.HashMap<String,String> requestData;
	/**
	 * 
	 */
	public abstract TransactionData amount(String amount);	
	/**
	 * 
	 */
	public abstract TransactionData currencyCode(String currencyCode);		
	/**
	 * 
	 */
	public abstract PaymentMethod paymentMethod();
	
	
		
	
	



}
