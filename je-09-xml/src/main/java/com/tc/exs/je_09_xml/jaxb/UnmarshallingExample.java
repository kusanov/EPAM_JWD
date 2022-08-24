package com.tc.exs.je_09_xml.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnmarshallingExample {

	// используйте правильные библиотеки!!!
	public static void main(String[] args) throws JAXBException {

		File file = new File("src\\main\\resources\\notes.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance("com.tc.exs.je_09_xml.jaxb");

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Notes notes = (Notes) jaxbUnmarshaller.unmarshal(file);

		for (Note n : notes.getNote()) {
			System.out.println(n.getBody());
		}

	}

}
