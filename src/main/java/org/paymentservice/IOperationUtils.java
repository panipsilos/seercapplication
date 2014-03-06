package org.paymentservice;

import org.paymentservice.datatypes.Operation;

public interface IOperationUtils {

	/**
	 * Contains operations which assist in executing the main operations
	 */
	 public String buildEndpoint(Operation operation, String identifier);

}
