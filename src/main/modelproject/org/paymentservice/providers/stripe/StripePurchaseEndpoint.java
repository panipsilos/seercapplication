/**
 * 
 */
package org.paymentservice.providers.stripe;



import org.paymentservice.PurchaseEndpoint;

/**
 * @author FGONIDIS
 *
 */
public class StripePurchaseEndpoint extends PurchaseEndpoint {

	public StripePurchaseEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}
	
	public String getEndpoint(String identifier){
		
		return super.baseEndpoint+"/charges";
	}

}
