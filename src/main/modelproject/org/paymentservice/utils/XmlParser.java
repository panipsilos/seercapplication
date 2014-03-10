package org.paymentservice.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParser {

	
	/**
	 * Reads XML from a string and returns the Document		
	 */
	public static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	} 
	
	public String readXmlElement(String xmlString, String elementName) throws Exception{
		Document doc = loadXMLFromString(xmlString);
		NodeList elementValue = doc.getElementsByTagName(elementName);
		return elementValue.item(0).getTextContent();
	}
	
}
