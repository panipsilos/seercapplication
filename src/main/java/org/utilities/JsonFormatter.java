package org.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

 public class  JsonFormatter {

	public static String parseJson(String inputString)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(inputString);
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}
	
	//reads a specific element in the json srtring
	public static String getJsonElement(String jsonLine, String element)
	{
		JsonParser jp = new JsonParser();
		JsonElement jsonElement =  jp.parse(jsonLine);

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		return jsonObject.get(element).toString();

	}

}
