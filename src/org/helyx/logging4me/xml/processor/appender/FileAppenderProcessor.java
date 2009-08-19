package org.helyx.logging4me.xml.processor.appender;

import java.util.Hashtable;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.appender.FileAppender;
import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.Layout;
import org.helyx.logging4me.xml.XmlParsingException;
import org.helyx.logging4me.xml.processor.ElementProcessorUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.kxml2.kdom.Element;

public class FileAppenderProcessor implements IElementProcessor {

	public Object process(Element element, XmlConfigurer xmlConfigurer) {
		FileAppender fileAppender = null;

		Hashtable propertyMap = ElementProcessorUtil.loadAttributesAsMap(element);
		if (!propertyMap.containsKey("filePath")) {
			throw new XmlParsingException("'filePath' property is mandatory for file appender element");
		}
		
		String filePath = (String)propertyMap.get("filePath");
		fileAppender = new FileAppender(filePath);
		
		if (propertyMap.containsKey("layout")) {
			String layoutName = (String)propertyMap.get("layout");
			Layout layout = xmlConfigurer.getLayout(layoutName);
			fileAppender.setLayout(layout);			
		}
		
		if (propertyMap.containsKey("thresholdLevel")) {
			String thresholdLevelStr = (String)propertyMap.get("thresholdLevel");
			int thresholdLevel = Logger.getLevelValue(thresholdLevelStr);
			fileAppender.setThresholdLevel(thresholdLevel);
		}

		return fileAppender;
	}

}
