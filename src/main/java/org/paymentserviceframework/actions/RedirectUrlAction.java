package org.paymentserviceframework.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RedirectUrlAction implements IAction{
	
	public String redirectUrl;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	}

	
	

}
