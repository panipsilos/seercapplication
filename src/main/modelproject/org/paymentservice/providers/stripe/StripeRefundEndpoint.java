/**
 * 
 */
package org.paymentservice.providers.stripe;

import org.paymentservice.RefundEndpoint;

/**
 * @author FGONIDIS
 *
 */
public class StripeRefundEndpoint extends RefundEndpoint {

	/**
	 * 
	 */
	public StripeRefundEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}
	
	public String getEndpoint(String identifier){
		return super.baseEndpoint+"/charges/"+identifier+"/refund";
	}

}
