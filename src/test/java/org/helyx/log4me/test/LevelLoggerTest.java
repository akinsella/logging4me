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
package org.helyx.log4me.test;

import java.util.Vector;

import junit.framework.TestCase;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.layout.Layout;
import org.helyx.logging4me.layout.SimpleLayout;

public class LevelLoggerTest extends TestCase {
	
	protected static final String TEST_CATEGORY = "TEST_CATEGORY";
	
	protected static final String TEST_APPENDER = "TEST_APPENDER";
	
	protected static final String TEST_CONTENT = "TEST_CONTENT";


	public void testTraceLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.TRACE, TEST_CATEGORY, Logger.TRACE, Logger.TRACE);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);		
		logger.trace(TEST_CONTENT);
		
		assertLoggerInformation(testAppender);	
	}
	
	public void testDebugLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.DEBUG, TEST_CATEGORY, Logger.DEBUG, Logger.DEBUG);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);
		logger.debug(TEST_CONTENT);
		assertLoggerInformation(testAppender);

		testAppender.clearResults();
		
		logger.trace(TEST_CONTENT);
		assertEquals(0, testAppender.getLogInformationList().size());
	}
	
	public void testInfoLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.INFO, TEST_CATEGORY, Logger.INFO, Logger.INFO);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);
		logger.info(TEST_CONTENT);
		assertLoggerInformation(testAppender);

		testAppender.clearResults();
		
		logger.debug(TEST_CONTENT);
		assertEquals(0, testAppender.getLogInformationList().size());
	}
	
	public void testWarnLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.WARN, TEST_CATEGORY, Logger.WARN, Logger.WARN);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);
		logger.warn(TEST_CONTENT);
		assertLoggerInformation(testAppender);

		testAppender.clearResults();
		
		logger.info(TEST_CONTENT);
		assertEquals(0, testAppender.getLogInformationList().size());
	}
	
	public void testErrorLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.ERROR, TEST_CATEGORY, Logger.ERROR, Logger.ERROR);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);
		logger.error(TEST_CONTENT);
		assertLoggerInformation(testAppender);

		testAppender.clearResults();
		
		logger.warn(TEST_CONTENT);
		assertEquals(0, testAppender.getLogInformationList().size());
	}
	
	public void testFatalLevel() {
		TestAppender testAppender = prepareLoggingSystem(Logger.FATAL, TEST_CATEGORY, Logger.FATAL, Logger.FATAL);
		
		Logger logger = Logger.getLogger(TEST_CATEGORY);
		logger.fatal(TEST_CONTENT);		
		assertLoggerInformation(testAppender);

		testAppender.clearResults();
		
		logger.error(TEST_CONTENT);
		assertEquals(0, testAppender.getLogInformationList().size());
	}
	
	private TestAppender prepareLoggingSystem(int loggerManagerThreasholdLevel, String categoryName, int testAppenderThresholdLevel, int categoryLevel) {
		LoggerManager.resetAll();
				
		LoggerManager.setThresholdLevel(loggerManagerThreasholdLevel);

		Layout simpleLayout = new SimpleLayout();
		
		TestAppender testAppender = new TestAppender();
		testAppender.setLayout(simpleLayout);
		testAppender.setThresholdLevel(testAppenderThresholdLevel);
		LoggerManager.registerAppender(testAppender);
		LoggerManager.getRootCategory().addAppender(testAppender);
		
		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(simpleLayout);
		consoleAppender.setThresholdLevel(Logger.TRACE);
		LoggerManager.getRootCategory().addAppender(consoleAppender);
		LoggerManager.registerAppender(consoleAppender);		
		LoggerManager.addCategory(categoryName, categoryLevel);
		
		return testAppender;
	}
	
	private void assertLoggerInformation(TestAppender testAppender) {
		
		Vector logInformationList = testAppender.getLogInformationList();
		
		assertEquals(1, logInformationList.size());
		
		LogInformation logInformation = (LogInformation)logInformationList.elementAt(0);
		
		assertNotNull(logInformation);

	}

}
