/**
 * 
 */
package org.paymentservice.providers.spreedly;


import org.paymentservice.PurchaseEndpoint;
import org.paymentservice.datatypes.Constants;

/**
 * @author FGONIDIS
 *
 */
public class SpreedlyPurchaseEndpoint extends PurchaseEndpoint {

	/**
	 * 
	 */
	public SpreedlyPurchaseEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}
	
	public String getEndpoint(String identifier){
		return super.baseEndpoint+"gateways/"+Constants.gatewayToken+"/purchase.xml";
	}

}
