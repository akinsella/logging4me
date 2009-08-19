package org.helyx.logging4me.xml.processor.layout;

import java.util.Hashtable;

import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.SimpleLayout;
import org.helyx.logging4me.xml.processor.ElementProcessorUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.kxml2.kdom.Element;

public class LevelLayoutProcessor implements IElementProcessor {

	public Object process(Element element, XmlConfigurer xmlConfigurer) {
		SimpleLayout simpleLayout = new SimpleLayout();
		Hashtable propertyMap = ElementProcessorUtil.loadAttributesAsMap(element);
		if (propertyMap.containsKey("separator")) {
			String separator = (String)propertyMap.get("separator");
			simpleLayout.setSeparator(separator);			
		}
		
		return simpleLayout;
	}

}
