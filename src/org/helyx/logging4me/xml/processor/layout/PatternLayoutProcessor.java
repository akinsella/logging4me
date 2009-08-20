package org.helyx.logging4me.xml.processor.layout;

import java.util.Hashtable;

import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.pattern.PatternLayout;
import org.helyx.logging4me.xml.processor.ElementProcessorUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.kxml2.kdom.Element;

public class PatternLayoutProcessor implements IElementProcessor {

	public Object process(Element element, XmlConfigurer xmlConfigurer) {
		PatternLayout patternLayout = null;
		Hashtable propertyMap = ElementProcessorUtil.loadAttributesAsMap(element);
		if (propertyMap.containsKey("pattern")) {
			String pattern = (String)propertyMap.get("pattern");
			patternLayout = new PatternLayout(pattern);
		}
		else {
			patternLayout = new PatternLayout();
		}
		
		return patternLayout;
	}

}
