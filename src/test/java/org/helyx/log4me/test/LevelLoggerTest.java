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
