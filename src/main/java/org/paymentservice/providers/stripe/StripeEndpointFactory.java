/**
 * 
 */
package org.paymentservice.providers.stripe;

import org.paymentservice.ProviderEndpointFactory;
import org.paymentservice.providers.spreedly.SpreedlyPurchaseEndpoint;

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

}
