/**
 * 
 */
package org.paymentservice.providers.spreedly;

import org.paymentservice.ProviderEndpointFactory;

/**
 * @author FGONIDIS
 *
 */
public class SpreedlyEndpointFactory extends ProviderEndpointFactory {
		
	
	/**
	 * calls super constructor and passes arguments
	 */
	public SpreedlyEndpointFactory(String baseEndpoint){
		super(baseEndpoint);
	}
	
	/**
	 * creates SpreedlyPurchaseEndoint and passes the baseEndpoint
	 */
	public SpreedlyPurchaseEndpoint createPurchaseEndpoint(){
		return new SpreedlyPurchaseEndpoint(super.baseEndpoint);
	}

}
