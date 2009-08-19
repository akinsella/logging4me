/**
 * Copyright (C) 2007-2009 Alexis Kinsella - http://www.helyx.org - <Helyx.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helyx.logging4me.config;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import org.helyx.basics4me.io.BufferedInputStream;
import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.appender.FileAppender;
import org.helyx.logging4me.layout.Layout;
import org.helyx.logging4me.layout.LevelLayout;
import org.helyx.logging4me.layout.SimpleLayout;
import org.helyx.logging4me.layout.pattern.PatternLayout;
import org.helyx.logging4me.stream.StreamUtil;
import org.helyx.logging4me.system.SystemLogger;
import org.helyx.logging4me.xml.XmlParsingException;
import org.helyx.logging4me.xml.dom.DomAttributeProcessor;
import org.helyx.logging4me.xml.dom.DomUtil;
import org.helyx.logging4me.xml.processor.IElementProcessor;
import org.helyx.logging4me.xml.processor.appender.ConsoleAppenderProcessor;
import org.helyx.logging4me.xml.processor.appender.FileAppenderProcessor;
import org.helyx.logging4me.xml.processor.layout.LevelLayoutProcessor;
import org.helyx.logging4me.xml.processor.layout.PatternLayoutProcessor;
import org.helyx.logging4me.xml.processor.layout.SimpleLayoutProcessor;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;


public class XmlConfigurer implements LoggerConfigurer {

	private static final String CAT = "XML_CONFIGURER";
	
	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
	
	private static final String ELT_LAYOUT = "layout";
	private static final String ELT_CATEGORY = "category";
	private static final String ELT_APPENDER = "appender";
	
	private static final String MICROEDITION_ENCODING = "microedition.encoding";
	
	private String resourcePath;
	private String encoding;
	
	private Hashtable elementProcessorRegistry = new Hashtable();
	
	private Hashtable layoutMap = new Hashtable();
	
	public XmlConfigurer(String resourcePath) {
		this.resourcePath = resourcePath;
		encoding = System.getProperty(MICROEDITION_ENCODING);
		if (encoding == null) {
			encoding = ENCODING_ISO_8859_1;
		}
		initDefaultElementProcessors();
	}

	public XmlConfigurer(String resourcePath, String encoding) {
		super();
		this.resourcePath = resourcePath;
		this.encoding = encoding;
		initDefaultElementProcessors();
	}

	private void initDefaultElementProcessors() {
		registerElementProcessor(SimpleLayout.class.getName(), new SimpleLayoutProcessor());
		registerElementProcessor(LevelLayout.class.getName(), new LevelLayoutProcessor());
		registerElementProcessor(PatternLayout.class.getName(), new PatternLayoutProcessor());
		registerElementProcessor(ConsoleAppender.class.getName(), new ConsoleAppenderProcessor());
		registerElementProcessor(FileAppender.class.getName(), new FileAppenderProcessor());
	}
	
	public void registerElementProcessor(String classname, IElementProcessor eltProcessor) {
		elementProcessorRegistry.put(classname, eltProcessor);	
	}
	
	public void configure() {
		try {
			InputStream is = StreamUtil.createsFromClassPath(resourcePath);
			BufferedInputStream bis = new BufferedInputStream(is);
			try {
				Document doc = DomUtil.parseDoc(bis, encoding);
				
				LoggerManager.resetAll();
				Element rootElt = doc.getRootElement();
				processRootElement(rootElt);
				
				Enumeration enumLayout = DomUtil.elementIterator(rootElt, ELT_LAYOUT);
				while (enumLayout.hasMoreElements()) {
					Element layoutElt = (Element)enumLayout.nextElement();
					processLayoutElement(layoutElt);
				}
				
				Enumeration enumAppender = DomUtil.elementIterator(rootElt, ELT_APPENDER);
				while (enumAppender.hasMoreElements()) {
					Element appenderElt = (Element)enumAppender.nextElement();
					processAppenderElement(appenderElt);
				}				
				
				Enumeration enumCategory = DomUtil.elementIterator(rootElt, ELT_CATEGORY);
				while (enumCategory.hasMoreElements()) {
					Element layoutElt = (Element)enumCategory.nextElement();
					processCategoryElement(layoutElt);
				}
			}
			finally {
				if (is != null) {
					try { is.close(); } finally { }
				}
				if (bis != null) {
					try { bis.close(); } finally { }
				}
			}
		}
		catch(Throwable t) {
			SystemLogger.debug(CAT, t.getMessage(), t);
		}
	}
	
	private void processRootElement(Element rootElt) {
		DomAttributeProcessor domAttributeProcessor = new DomAttributeProcessor();
		domAttributeProcessor.processElement(rootElt);
		
		String thresholdLevelStr = domAttributeProcessor.getAttrValueAsString("thresholdLevel");
		if (thresholdLevelStr == null) {
			throw new XmlParsingException("'thresholdLevel' parameter is mandatory for logging4me element");
		}
		int thresholdLevel = Logger.getLevelValue(thresholdLevelStr);
		LoggerManager.setThresholdLevel(thresholdLevel);

		if (domAttributeProcessor.attrExists("debugMode")) {
			boolean debugMode = domAttributeProcessor.getAttrValueAsBoolean("debugMode");
			LoggerManager.setDebugMode(debugMode);
		}
	}

	private void processCategoryElement(Element layoutElement) {
		DomAttributeProcessor domAttributeProcessor = new DomAttributeProcessor();
		domAttributeProcessor.processElement(layoutElement);
		String categoryName = domAttributeProcessor.getAttrValueAsString("name");
		if (categoryName == null) {
			throw new XmlParsingException("'name' parameter is mandatory for category element");
		}

		String levelStr = domAttributeProcessor.getAttrValueAsString("level");
		if (levelStr == null) {
			throw new XmlParsingException("'level' parameter is mandatory for category element");
		}
		int level = Logger.getLevelValue(levelStr);

		LoggerManager.addCategory(categoryName, level);
	}
	
	private void processLayoutElement(Element element) {
		DomAttributeProcessor domAttributeProcessor = new DomAttributeProcessor();
		domAttributeProcessor.processElement(element);
		String nameStr = domAttributeProcessor.getAttrValueAsString("name");
		if (nameStr == null) {
			throw new XmlParsingException("'name' parameter is mandatory for layout element");
		}
		String classStr = domAttributeProcessor.getAttrValueAsString("class");
		if (classStr == null) {
			throw new XmlParsingException("'class' parameter is mandatory for layout element");
		}
		IElementProcessor elementProcessor = (IElementProcessor)elementProcessorRegistry.get(classStr);
		Layout layout = (Layout)elementProcessor.process(element, this);
		layoutMap.put(nameStr, layout);
	}
	
	private void processAppenderElement(Element element) {
		DomAttributeProcessor domAttributeProcessor = new DomAttributeProcessor();
		domAttributeProcessor.processElement(element);

		String nameStr = domAttributeProcessor.getAttrValueAsString("name");
		if (nameStr == null) {
			throw new XmlParsingException("'name' parameter is mandatory for appender element");
		}
		
		String classStr = domAttributeProcessor.getAttrValueAsString("class");
		if (classStr == null) {
			throw new XmlParsingException("'class' parameter is mandatory for layout element");
		}
		
		IElementProcessor elementProcessor = (IElementProcessor)elementProcessorRegistry.get(classStr);
		Appender appender = (Appender)elementProcessor.process(element, this);
		try {
			appender.open();
			LoggerManager.registerAppender(appender);
			LoggerManager.getRootCategory().addAppender(appender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Layout getLayout(String layoutName) {
		return (Layout)layoutMap.get(layoutName);
	}
	
}
