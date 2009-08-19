package org.helyx.logging4me.xml.processor;

import java.util.Enumeration;
import java.util.Hashtable;

import org.helyx.logging4me.xml.dom.DomAttributeProcessor;
import org.helyx.logging4me.xml.dom.DomUtil;
import org.kxml2.kdom.Element;

public class ElementProcessorUtil {

	private static final String ELT_PROPERTY = "property";
	
	private ElementProcessorUtil() {
		super();
	}
	
	public static Hashtable loadAttributesAsMap(Element element) {
		Enumeration enumProperty = DomUtil.elementIterator(element, ELT_PROPERTY);
		Hashtable propertyMap = new Hashtable();
		while (enumProperty.hasMoreElements()) {
			Element propertyElt = (Element)enumProperty.nextElement();
			DomAttributeProcessor dap = new DomAttributeProcessor();
			dap.processElement(propertyElt);

			String propertyName = dap.getAttrValueAsString("name");
			String propertyValue = dap.getAttrValueAsString("value");
			propertyMap.put(propertyName, propertyValue);
		}

		return propertyMap;
	}

}
