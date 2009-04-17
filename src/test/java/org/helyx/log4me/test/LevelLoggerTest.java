/**
 * Copyright (C) 2007-2009 Alexis Kinsella - $ {website} - <Helyx.org>
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
package org.helyx.log4me.test;

import junit.framework.TestCase;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerFactory;
import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.layout.SimpleLayout;

public class LevelLoggerTest extends TestCase {
	
	protected static final String TEST_CATEGORY = "TEST_CATEGORY";
	
	protected static final String TEST_APPENDER = "TEST_APPENDER";
	
	protected static final String TEST_CONTENT = "TEST_CONTENT";

	
	public void testLogger() throws InterruptedException {
		
		LoggerManager.setThresholdLevel(Logger.DEBUG);
		
		LoggerManager.removeAllCateogries();
		LoggerManager.closeAllAppenders();
		
		ConsoleAppender consoleAppender = ConsoleAppender.getInstance();
		consoleAppender.setLayout(new SimpleLayout());
		
		LoggerManager.addAppender(consoleAppender);
		
		LoggerManager.addCategory(TEST_CATEGORY, consoleAppender.getName(), Logger.INFO);

		final Logger logger = LoggerFactory.getLogger(TEST_CATEGORY);		
		
		logger.info(TEST_CONTENT);

	}

}
