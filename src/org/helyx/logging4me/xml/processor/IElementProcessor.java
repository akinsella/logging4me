package org.helyx.logging4me.xml.processor;

import org.helyx.logging4me.config.XmlConfigurer;
import org.kxml2.kdom.Element;

public interface IElementProcessor {

	Object process(Element element, XmlConfigurer xmlConfigurer);
	
}
