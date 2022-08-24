package com.tc.exs.je_09_xml.jdom;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class JDomMenuParser {

	public static void main(String[] args) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build("src/main/resources/breakfastmenu.xml");
		
		Element root = document.getRootElement();
		List<Element> menu = root.getChildren();
		Iterator<Element> menuIterator = menu.iterator();
		while (menuIterator.hasNext()){
			Element foodElement = menuIterator.next();
			System.out.println(foodElement.getChildText("name"));
		}
	}
}
