/**
 * 
 */
package org.paymentservice;

/**
 * @author FGONIDIS
 * 
 */
public abstract class RefundEndpoint {

	/**
	 * 
	 */
	public String baseEndpoint;

	/**
	 * 
	 */
	public RefundEndpoint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * returns the endpoint for this operation
	 */
	public String getEndpoint(String identifier) {
		return null;
	}

}
