/**
 * 
 */
package org.paymentservice;

import org.paymentservice.datatypes.Operation;



/**
 * @author FGONIDIS
 *
 */
public  class BaseOperationUtils implements IOperationUtils {

	private ProviderEndpointFactory providerEndpointFactory;
	private PurchaseEndpoint purchaseEndpoint;
	/**
	 * 
	 */
	 private Operation operation;

	/**
	 * 
	 */
	public BaseOperationUtils(ProviderEndpointFactory providerEndpointFactory) {
		
		// TODO Auto-generated constructor stub
		this.providerEndpointFactory = providerEndpointFactory;
	}
	
	public String buildEndpoint(Operation operation, String identifier){
		this.operation = operation;
		return this.getOperationEndpoint(identifier);
	}

	/**
	 * decides for which operation, to get the endpoint.
	 *  Internal in the abstract class. No need to be public
	 */
	 private String getOperationEndpoint(String identifier) {
		
		 switch (operation) {
		 	case PURCHASE:
		 		purchaseEndpoint = providerEndpointFactory.createPurchaseEndpoint();
		 		return purchaseEndpoint.getEndpoint(identifier);		 		
		 }
		 // else exception, unsupported operation
		 return null;
	}

}
