package com.tc.exs.je_09_xml.jdom;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class JomCreateXML {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		Element root = new Element("breakfast-menu");
		Element food = new Element("food");
		food.setAttribute("id", "123");
		Element name = new Element("name");
		name.setText("Waffles");
		food.addContent(name);
		root.addContent(food);
		Document document = new Document(root);
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(document, new FileOutputStream("newmenu.xml"));
	}
}
