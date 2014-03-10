/**
 * 
 */
package org.paymentservice.providers.spreedly;

import org.paymentservice.datamodel.PaymentMethod;
import org.paymentservice.datamodel.TransactionData;



/**
 * @author FGONIDIS
 *
 */
public class SpreedlyPaymentMethod extends PaymentMethod {

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
	public SpreedlyPaymentMethod(SpreedlyTransactionData parent) {
		this.parent = parent;
	}

	/**
	 * token which identifies the credit card
	 */
	public SpreedlyPaymentMethod token(String token) {
		parent.requestData.put("transaction[payment_method_token]", token);
		return this;
	}
	
	public TransactionData done(){
		return parent;
	}

}
