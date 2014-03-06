/**
 * 
 */
package org.paymentservice;

/**
 * @author FGONIDIS
 *
 */
public abstract class PurchaseEndpoint {

	/**
	 * 		
	 */
	 public String baseEndpoint;
	/**
	 * Enumeration Operation holds the type of operation to get the endpoint for
	 */
	private org.paymentservice.datatypes.Operation operation;

	/**
	 * 
	 */
	public PurchaseEndpoint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 		
	 */
	 public String getEndpoint(String identifier) {
		return null;
	}

}
