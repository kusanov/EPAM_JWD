package com.tc.exs.je_08_xml.dom;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMWriterExample {

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document = builder.newDocument();
	 
	    Element breakfastMenu = document.createElement("breakfast-menu");
	    Element food = document.createElement("food");
	    food.setAttribute("id", "234");
	    
	    Element name = document.createElement("name");
	    name.setTextContent("Waffles");
	    
	    food.appendChild(name);
	    breakfastMenu.appendChild(food);
	    document.appendChild(breakfastMenu);
	    
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileWriter("dommenu.xml"));
        transformer.transform(source, result);
	}

}
