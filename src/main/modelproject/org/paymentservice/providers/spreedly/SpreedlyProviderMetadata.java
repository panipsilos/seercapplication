/**
 * 
 */
package org.paymentservice.providers.spreedly;

import org.paymentservice.ProviderMetadata;

/**
 * @author FGONIDIS
 *
 */
public class SpreedlyProviderMetadata extends ProviderMetadata {

	/**
	 * 
	 */
	public SpreedlyProviderMetadata() {
		baseEndpoint("https://core.spreedly.com/v1/");
	}

}
