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
