/**
 * 
 */
package org.paymentservice;

/**
 * @author FGONIDIS
 *
 */
public abstract class ProviderMetadata {

	/**
	 * Holds the base endpoint of the provider to make the Http requests
	 */
	 java.lang.String baseEndpoint;

	/**
	 * 
	 */
	public ProviderMetadata() {
		// TODO Auto-generated constructor stub
	}
	
	public ProviderMetadata baseEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
		return this;
	}
	

}
