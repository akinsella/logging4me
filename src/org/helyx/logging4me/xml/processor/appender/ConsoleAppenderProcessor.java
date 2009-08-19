package org.helyx.logging4me.xml.processor.appender;

import java.util.Hashtable;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.Layout;
import org.helyx.logging4me.xml.processor.ElementProcessorUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.kxml2.kdom.Element;

public class ConsoleAppenderProcessor implements IElementProcessor {

	public Object process(Element element, XmlConfigurer xmlConfigurer) {
		ConsoleAppender consoleAppender = new ConsoleAppender();
		Hashtable propertyMap = ElementProcessorUtil.loadAttributesAsMap(element);
		if (propertyMap.containsKey("layout")) {
			String layoutName = (String)propertyMap.get("layout");
			Layout layout = xmlConfigurer.getLayout(layoutName);
			consoleAppender.setLayout(layout);			
		}
		
		if (propertyMap.containsKey("thresholdLevel")) {
			String thresholdLevelStr = (String)propertyMap.get("thresholdLevel");
			int thresholdLevel = Logger.getLevelValue(thresholdLevelStr);
			consoleAppender.setThresholdLevel(thresholdLevel);
		}

		return consoleAppender;
	}

}
