/**
 * 
 */
package org.paymentservice.providers.stripe;

import org.paymentservice.datamodel.PaymentMethod;
import org.paymentservice.datamodel.TransactionData;



/**
 * @author FGONIDIS
 *
 */
public class StripePaymentMethod extends PaymentMethod {

	/**
	 * token which identifies the credit card
	 */
	private java.lang.String token;

	/**
	 * 
	 */
	private TransactionData parent;
	/**
	 * 
	 */
	public StripePaymentMethod(StripeTransactionData parent) {
		this.parent = parent;
	}

	/**
	 * token which identifies the credit card
	 */
	public StripePaymentMethod token(String token) {
		parent.requestData.put("card", token);
		return this;
	}
	
	public TransactionData done(){
		return parent;
	}

}
