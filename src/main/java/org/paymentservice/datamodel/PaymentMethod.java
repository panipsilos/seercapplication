/**
 * 
 */
package org.paymentservice.datamodel;

/**
 * @author FGONIDIS
 * 
 */
public abstract class PaymentMethod {

	/**
	 * identifies the credit card
	 */
	private java.lang.String token;

	/**
	 *  
	 */
	private TransactionData parent;
	
	public PaymentMethod(){}
	
	public  PaymentMethod (TransactionData parent) {
		this.parent = parent;
	}
	
	public String getToken(){
		return token;
	}

	/**
	 * 
	 */
	public abstract PaymentMethod token(String token);
	
	public abstract TransactionData done();

}
