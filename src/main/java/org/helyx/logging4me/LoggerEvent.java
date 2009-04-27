package org.helyx.logging4me;

import java.util.Date;

public class LoggerEvent {

	public final int level;
	
	public final Logger logger;
	
	public final String message;
	
	public final long time;
	
	public final Date date;

	public LoggerEvent(int level, Logger logger, String message) {
		super();
		this.level = level;
		this.logger = logger;
		this.message = message;
		this.time = System.currentTimeMillis();
		this.date = new Date(time);
	}

	public LoggerEvent(int level, Logger logger, String message, Date date) {
		super();
		this.level = level;
		this.logger = logger;
		this.message = message;
		this.time = date.getTime();
		this.date = date;
	}
	
}
