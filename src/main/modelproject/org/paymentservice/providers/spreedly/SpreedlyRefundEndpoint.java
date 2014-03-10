/**
 * 
 */
package org.paymentservice.providers.spreedly;

import org.paymentservice.RefundEndpoint;
import org.paymentservice.datatypes.Constants;

/**
 * @author FGONIDIS
 *
 */
public class SpreedlyRefundEndpoint extends RefundEndpoint {

	/**
	 * 
	 */
	public SpreedlyRefundEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}
	
	public String getEndpoint(String identifier){
		return super.baseEndpoint+"transactions/"+identifier+"/credit.xml";
	}
}
