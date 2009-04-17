package org.helyx.logging4me.appender;

import java.util.Date;

import org.helyx.logging4me.Logger;

public class LogInformation {

	private int level;
	
	private String message;
	
	private String resultMessage;
	
	private Date date;
	
	private Logger logger;

	public LogInformation(int level, Logger logger, String message, Date date, String resultMessage) {
		super();
		this.level = level;
		this.message = message;
		this.resultMessage = resultMessage;
		this.date = date;
		this.logger = logger;
	}

	public LogInformation() {
		super();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
}
