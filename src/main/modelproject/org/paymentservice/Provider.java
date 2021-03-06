/**
 * 
 *
 */
package org.paymentservice;

import org.paymentservice.providers.spreedly.SpreedlyEndpointFactory;
import org.paymentservice.providers.spreedly.SpreedlyProviderMetadata;
import org.paymentservice.providers.stripe.StripeEndpointFactory;
import org.paymentservice.providers.stripe.StripeProviderMetadata;
import org.paymentservice.utils.Http;


/**
 * @author FGONIDIS
 * 
 */
public class Provider {

	private Http http;
	private ProviderMetadata providerMetadata;
	private IOperations operations;
	private ProviderEndpointFactory providerEndpointFactory;
	private IOperationUtils operationUtils;

	/**
	 * Username:password for basic auth
	 */
	public String credentials;

	public IOperations getOperator() {
		return operations;
	}

	public String getCredentials() {
		return credentials;
	}

	/**
	 * Sould be generated based on ServiceLoader and reflection. see jClouds
	 * Currently it s done via an integer. Spreedly =1 , Stripe = 2 It also
	 * takes the credentials in order to instantiate the Http operator
	 * @throws Exception 
	 */
	public  Provider(int providerId, String username, String password) throws Exception {
		
		// instantiate the Http
		http = new Http(username, password);
		
		switch (providerId) {
		case 1: {
			instantiateSpreedly();
			break;
		}
		case 2: {
			instantiateStripe();
		}
		}
		// instantiate the Provider Endpoint Factory
		//operationUtils = new BaseOperationUtils(providerEndpointFactory);		

		// instantiate the Http
		//http = new Http(username, password);
		
		// instantiate Base operations
		//operations = new BaseOperations(operationUtils,http);

	}

	/**
	 * Not used. Auto-generated by UML tool
	 */
	public Provider(IOperations operations2,
			ProviderMetadata providerMetadata2, Http http2) {
		operations = operations2;
		// TODO Auto-generated constructor stub
		providerMetadata = providerMetadata2;
		http = http2;
	}

	public void instantiateSpreedly() throws Exception {
		providerMetadata = new SpreedlyProviderMetadata();
		providerEndpointFactory = new SpreedlyEndpointFactory(
				providerMetadata.baseEndpoint);
		operationUtils = new BaseOperationUtils(providerEndpointFactory);			
		operations = new BaseOperations(operationUtils,http);
		
		/**
		 * spreedly specific actions, add gateway.Gateway info should be added using an XML
		 * gateway token should be stored back in a XML. It s better to move this action to pre-operation actions. 
		 * Since it s only valid for specific operations
		 */
//		if(Constants.gatewayToken==null){
//		HashMap<String,String> requestData = new HashMap<String,String>();
//		requestData.put("gateway[gateway_type]", "test");
//		String response = new SpreedlyOperations(operationUtils, http).addGateway(requestData);
//		
//		//parse xml and store token value to constants file.
//		Constants.gatewayToken = new XmlParser().readXmlElement(response, "token");
//		}
	}

	public void instantiateStripe() {
		providerMetadata = new StripeProviderMetadata();
		providerEndpointFactory = new StripeEndpointFactory(
				providerMetadata.baseEndpoint);
		operationUtils = new BaseOperationUtils(providerEndpointFactory);	
		operations = new BaseOperations(operationUtils,http);
	}

	/**
	 * builder pattern for adding credentials
	 */
	public Provider credentials(String username, String password) {
		this.credentials = username + ":" + password;
		return this;
	}
}
