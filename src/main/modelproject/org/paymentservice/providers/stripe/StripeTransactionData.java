/**
 * 
 */
package org.paymentservice.providers.stripe;


import org.paymentservice.datamodel.TransactionData;



/**
 * @author FGONIDIS
 * 
 */
public class StripeTransactionData extends TransactionData {

	private StripePaymentMethod stripePaymentMethod;

	/**
	 * 
	 */
	public StripeTransactionData() {

		// TODO Auto-generated constructor stub
	}

	public TransactionData amount(String amount) {
		super.requestData.put("amount", amount);
		return this;
	}

	/**
		 * 
		 */
	public TransactionData currencyCode(String currencyCode) {
		super.requestData.put("currency", currencyCode);
		return this;
	}

	/**
		 * call the payment method and adds the transaction data object once "done".
		 */
	public StripePaymentMethod paymentMethod() {
		stripePaymentMethod = new StripePaymentMethod(this);
		return stripePaymentMethod;
	}

}
