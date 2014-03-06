/**
 * 
 */
package org.paymentservice.providers.spreedly;

import java.util.Map;

import org.paymentservice.BaseOperations;
import org.paymentservice.IOperationUtils;
import org.paymentservice.IOperations;
import org.paymentservice.utils.Http;

/**
 * @author FGONIDIS
 *
 */
public class SpreedlyOperations extends BaseOperations {

	/**
	 * 
	 */
	public SpreedlyOperations(IOperationUtils operationUtils, Http http) {
		
		// Set properties of super class. Can the super constructor be called automatically 
		// and set those properties directly in the super class???
		super.setHttp(http);
		super.setOperationUtils(operationUtils);
	}

	/**
	 * Specific Spreedly action for adding the gateway. It is invoked from the Provider upon initialisation
	 */
	 public String addGateway(Map<String, String> requestData) {		
		//for provider specific operations, endpoint can be hardcoded. Maybe I need to place it via the endpoint factory.
		String endpoint = "https://core.spreedly.com/v1/gateways.xml";
		String response = super.getHttp().httpRequest(endpoint, null, requestData);  
		return response;
	}
}
