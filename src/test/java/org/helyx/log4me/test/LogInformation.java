package org.helyx.log4me.test;

import org.helyx.logging4me.LoggerEvent;

public class LogInformation {

	public final LoggerEvent loggerEvent;
	
	public final String resultMessage;

	public LogInformation(LoggerEvent loggerEvent, String resultMessage) {
		super();
		this.loggerEvent = loggerEvent;
		this.resultMessage = resultMessage;
	}


	
}
