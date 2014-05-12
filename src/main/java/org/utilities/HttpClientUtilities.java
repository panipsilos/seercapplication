	package org.utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class HttpClientUtilities {
		
	public String getParameters(String url, String parameter)
	{
		// "http://seerc.org/ikm/index.php?option=com_content&view=article&id=14&Itemid=14";
		List<NameValuePair> params = null;
		try {
			params = URLEncodedUtils.parse(new URI(url), "UTF-8");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (NameValuePair param : params) {
		  System.out.println(param.getName() + " : " + param.getValue());
		  if(param.getName().compareTo(parameter)==0){
			return param.getValue();
		  }
		}
		return "NoParameterFound";
	}

}



