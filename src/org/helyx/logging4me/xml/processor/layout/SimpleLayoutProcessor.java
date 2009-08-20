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
