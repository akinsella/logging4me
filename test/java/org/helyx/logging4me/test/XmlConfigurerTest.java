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
package org.helyx.logging4me.test;

import junit.framework.TestCase;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.category.Category;
import org.helyx.logging4me.config.LoggerConfigurer;
import org.helyx.logging4me.config.XmlConfigurer;
import org.helyx.logging4me.layout.pattern.PatternLayout;

public class XmlConfigurerTest extends TestCase {

	public void testXmlConfigurer() {
		LoggerConfigurer loggerConfigurer = new XmlConfigurer("/org/helyx/logging4me/test/logging4me.xml", true);
		loggerConfigurer.configure();
		assertTrue(LoggerManager.isDebugMode());
		assertEquals(Logger.DEBUG, LoggerManager.getThresholdLevel());
		Category cateogry = LoggerManager.getCategory("org.helyx.logging4me");
		assertNotNull(cateogry);
		assertEquals(Logger.DEBUG, cateogry.getLevel());
		assertEquals("org.helyx.logging4me", cateogry.getName());
		
		Appender consoleAppender = LoggerManager.getAppender("CONSOLE");
		assertNotNull(consoleAppender);
		assertEquals("CONSOLE", consoleAppender.getName());
		assertTrue(consoleAppender.isOpened());
		assertEquals(Logger.INFO, consoleAppender.getThresholdLevel());
		PatternLayout patternLayout = (PatternLayout)consoleAppender.getLayout();
		assertNotNull(patternLayout);
		assertEquals("|%T|%L|%C|%D{yyyy/MM/dd, HH:mm:ss.ZZ}| ", patternLayout.getPattern());
//		assertNotNull(LoggerManager.getAppender("FILE"));
	}	
}
