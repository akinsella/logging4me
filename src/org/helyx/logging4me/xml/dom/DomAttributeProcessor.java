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
package org.helyx.logging4me.xml.dom;

import java.util.Hashtable;

import org.helyx.logging4me.system.SystemLogger;
import org.kxml2.kdom.Element;

public class DomAttributeProcessor {
	
	private static final String CAT = "XPP_ATTRIBUTE_PROCESSOR";
	
	private static final String TRUE = "true";
	
	protected Hashtable attributeMap  = new Hashtable();
		
	public DomAttributeProcessor() {
		super();
	}

	public static DomAttributeProcessor getInstance(Element element) {
		DomAttributeProcessor xppAttributeProcessor = new DomAttributeProcessor();
		xppAttributeProcessor.processElement(element);
		
		return xppAttributeProcessor;
	}

	public boolean attrExists(String attributeName) {
		return attributeMap.containsKey(attributeName);
	}
	
	public boolean getAttrValueAsBoolean(String attributeName) {
		String booleanValue = (String)attributeMap.get(attributeName);
		
		if (booleanValue == null || booleanValue.length() == 0) {
			return false;
		}
		return TRUE.compareTo(booleanValue.toLowerCase()) == 0;
	}
	
	public byte getAttrValueAsByte(String attributeName) {
		String byteValue = (String)attributeMap.get(attributeName);
		
		if (byteValue == null || byteValue.length() == 0) {
			return 0;
		}
		return Byte.parseByte(byteValue);
	}
	
	public short getAttrValueAsShort(String attributeName) {
		String shortValue = (String)attributeMap.get(attributeName);
		
		if (shortValue == null || shortValue.length() == 0) {
			return 0;
		}
		return Short.parseShort(shortValue);
	}
	
	public int getAttrValueAsInt(String attributeName) {
		String intValue = (String)attributeMap.get(attributeName);
		
		if (intValue == null || intValue.length() == 0) {
			return 0;
		}
		return Integer.parseInt(intValue);
	}
	
	public long getAttrValueAsLong(String attributeName) {
		String longValue = (String)attributeMap.get(attributeName);
		
		if (longValue == null || longValue.length() == 0) {
			return 0;
		}
		return Long.parseLong(longValue);
	}

	public float getAttrValueAsFloat(String attributeName) {
		String floatValue = (String)attributeMap.get(attributeName);
		
		if (floatValue == null || floatValue.length() == 0) {
			return 0;
		}
		return Float.parseFloat(floatValue);
	}
	
	public double getAttrValueAsDouble(String attributeName) {
		String doubleValue = (String)attributeMap.get(attributeName);
		
		if (doubleValue == null || doubleValue.length() == 0) {
			return 0;
		}
		return Double.parseDouble((String)attributeMap.get(attributeName));
	}
	
	public String getAttrValueAsString(String attributeName) {
		return (String)attributeMap.get(attributeName);
	}
	public void processElement(Element element) {
		attributeMap.clear();
		int attrCount = element.getAttributeCount();
		String elementName = element.getName();
		SystemLogger.debug(CAT, "Element '" + elementName + "' attribute count: " + attrCount);
		for (int i = 0 ; i < attrCount ; i++) {
			String attributeName = element.getAttributeName(i);
			SystemLogger.debug(CAT, "Attribute[" + i + "] for Element[" + elementName + "] name: '" + attributeName + "'");
			String attributeValue = element.getAttributeValue(i);
			SystemLogger.debug(CAT, "Associating value: '" +  attributeValue + "' to attribute name='" + attributeName + "'");
			attributeMap.put(attributeName, attributeValue);
		}
	}

}
