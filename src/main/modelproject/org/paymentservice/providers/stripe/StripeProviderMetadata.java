/**
 * 
 */
package org.paymentservice.providers.stripe;

import org.paymentservice.ProviderMetadata;

/**
 * @author FGONIDIS
 *
 */
public class StripeProviderMetadata extends ProviderMetadata {

	/**
	 * 
	 */
	public StripeProviderMetadata() {
		super.baseEndpoint("https://api.stripe.com/v1");
		
	}

}
