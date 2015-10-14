package com.tl.dom4j.readXML;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
								"<test><aaa>aaacontent</aaa><bbb><ccc>cccContents</ccc></bbb></test>" +
							"</crs>";
		Document document = parse(source);
		Element root = document.getRootElement();
		/*for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
			Element el = (Element)it.next();
			System.out.println(el.getName() + "--" + el.getStringValue());
			List<?> childs = el.elements();
			System.out.println(childs);
			if (childs != null && childs.size() > 0) {
				for (int i = 0; i < childs.size(); i++) {
					Element child = (Element) childs.get(i);
					System.out.println(child.getName() + "==" + child.getStringValue());	
				}
			}
		}*/
		Map<String, String> contents = new HashMap<String, String>();
		parseToMap(root, contents);
		System.out.println(index);
		System.out.println(contents);
	}
	private static int index = 0;

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
	
	public static void parseToMap(Element root, Map<String, String> container) {
		index++;
		List<?> childs = root.elements();
		if (childs == null || childs.size() == 0) {
			container.put(root.getName(), root.getStringValue());
		} else {
			for (int i = 0; i < childs.size(); i++) {
				Element child = (Element)childs.get(i);
				parseToMap(child, container);
			}

		}
	}
}
