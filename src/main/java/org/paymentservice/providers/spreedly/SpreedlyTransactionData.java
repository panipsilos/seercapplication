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
public class SpreedlyTransactionData extends TransactionData {

	private SpreedlyPaymentMethod spreedlyPaymentMethod;

	/**
	 * 
	 */
	public SpreedlyTransactionData() {

		// TODO Auto-generated constructor stub
	}

	public TransactionData amount(String amount) {
		super.requestData.put("transaction[amount]", amount);
		return this;
	}

	/**
		 * 
		 */
	public TransactionData currencyCode(String currencyCode) {
		super.requestData.put("transaction[currency_code]", currencyCode);
		return this;
	}

	/**
		 * calls the payment method and adds the transaction data object once "done".
		 */
	public SpreedlyPaymentMethod paymentMethod() {
		spreedlyPaymentMethod = new SpreedlyPaymentMethod(this);
		return spreedlyPaymentMethod;
	}

}
