package com.tl.dom4j.readXML;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser {

	public static void main(String[] args) {
		String source = "<?xml version='1.0' encoding='UTF-8'?>" + 
							"<crs>" + 
								"<code>CSD000</code>"	+ 
								"<msg>处理成功</msg>"		+ 
								"<user_id>20140153</user_id>" + 
							"</crs>";
		Document document = parse(source);
		Element root = document.getRootElement();
		for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
			Element el = it.next();
			System.out.println();
			System.out.println(el.getName() + "--" + el.getStringValue());
		}
	}

	public static Document parse(String xmlSource) {
		SAXReader reader = new SAXReader();
		InputStream in = new ByteArrayInputStream(xmlSource.getBytes());
		Document document = null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
}
