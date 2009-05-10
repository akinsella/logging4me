package org.helyx.logging4me.config;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.layout.SimpleLayout;

public class BasicConfigurer implements LoggerConfigurer {

	private BasicConfigurer() {
		super();
	}
	
	public void configure() {
		LoggerManager.setThresholdLevel(Logger.DEBUG);
		
		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(new SimpleLayout());
		consoleAppender.setThresholdLevel(Logger.DEBUG);
		
		LoggerManager.registerAppender(consoleAppender);
	}

}
