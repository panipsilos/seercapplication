/**
 * 
 */
package org.paymentservice;

/**
 * @author FGONIDIS
 *
 */
public abstract class ProviderEndpointFactory {

	/**
	 * Read the baseEndpoint from the ProviderMetadata
	 */
	 public String baseEndpoint;


	/**
	 *  Default constructor
	 */
	
	public ProviderEndpointFactory(){}
	
	
	public ProviderEndpointFactory(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}


	/**
	 * Create the class PurchasEndpoint
	 */
	 public PurchaseEndpoint createPurchaseEndpoint() {
		return null;
	}

}
