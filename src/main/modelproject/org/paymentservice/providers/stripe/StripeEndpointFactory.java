/**
 * 
 */
package org.paymentservice.providers.stripe;

import org.paymentservice.ProviderEndpointFactory;


/**
 * @author FGONIDIS
 *
 */
public class StripeEndpointFactory extends ProviderEndpointFactory {

	
	
	/**
	 * calls super constructor and passes arguments
	 */
	public StripeEndpointFactory(String baseEndpoint){
		super(baseEndpoint);
	}
	
	/**
	 * creates SpreedlyPurchaseEndoint and passes the baseEndpoint
	 */
	public StripePurchaseEndpoint createPurchaseEndpoint(){
		return new StripePurchaseEndpoint(super.baseEndpoint);
	}
	
	public StripeRefundEndpoint createRefundEndpoint(){
		return new StripeRefundEndpoint(super.baseEndpoint);
	}

}
