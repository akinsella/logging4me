package org.helyx.logging4me.xml.processor.layout;

import java.util.Hashtable;

import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.LevelLayout;
import org.helyx.logging4me.xml.processor.ElementProcessorUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.kxml2.kdom.Element;

public class SimpleLayoutProcessor implements IElementProcessor {

	public Object process(Element element, XmlConfigurer xmlConfigurer) {
		LevelLayout levelLayout = new LevelLayout();
		Hashtable propertyMap = ElementProcessorUtil.loadAttributesAsMap(element);
		if (propertyMap.containsKey("separator")) {
			String separator = (String)propertyMap.get("separator");
			levelLayout.setSeparator(separator);			
		}
		
		return levelLayout;
	}

}
