package org.helyx.logging4me;

import java.util.Date;
import java.util.Enumeration;

import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.category.Category;
import org.helyx.logging4me.text.StringFormat;

public class Logger {

	public static final int FATAL = 6;
	public static final int ERROR = 5;
	public static final int WARN = 4;
	public static final int INFO = 3;
	public static final int DEBUG = 2;
	public static final int TRACE = 1;

	public static final String FATAL_STR = "FATAL";
	public static final String ERROR_STR = "ERROR";
	public static final String WARN_STR = "WARN";
	public static final String INFO_STR = "INFO";
	public static final String DEBUG_STR = "DEBUG";
	public static final String TRACE_STR = "TRACE";
	
	private static final String NULL_STR = "null";
	
	private Category category;
	
	public Logger(Category category) {
		super();
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public static String getLevelName(int level) {
		switch(level) {
			case Logger.FATAL:
				return FATAL_STR;
			case Logger.ERROR:
				return ERROR_STR;
			case Logger.WARN:
				return WARN_STR;
			case Logger.INFO:
				return INFO_STR;
			case Logger.DEBUG:
				return DEBUG_STR;
			case Logger.TRACE:
				return TRACE_STR;
			default:
				return ERROR_STR;
		}
	}


	private String getThrowableMessage(Throwable throwable) {
		return "Exception : " + throwable.getMessage() + " - " + throwable.getClass().getName();
	}

	private void write(int level, Object object) {
//		System.err.println("level: " + level + ", thresholdLevel: "  + thresholdLevel);
		if (!LoggerManager.isLoggable(level)) {
			return; 
		}
		
		if (object == null) {
			object = NULL_STR;
		}

		Date date = new Date();

		
		String message = null;
		if (object instanceof String) {
			message = (String)object;
		}
		else if (object instanceof Throwable) {
			Throwable throwable = (Throwable)object;
			throwable.printStackTrace();
			message = getThrowableMessage(throwable);
		}
		else {
			message = object.toString();
		}
		
		Enumeration _enum = category.getAppenderCacheList().elements();
		while (_enum.hasMoreElements()) {
			Appender appender = (Appender)_enum.nextElement();
			if (appender != null && category.isLoggable(level)) {
				appender.write(level, this, message, date);	
			}			
		}
	}

	

	public void fatal(Object object) {
		write(Logger.FATAL, object);
	}
	
	public void error(Object object) {
		write(Logger.ERROR, object);
	}
	
	public void warn(Object object) {
		write(Logger.WARN, object);
	}
	
	public void info(Object object) {
		write(Logger.INFO, object);
	}
	
	public void debug(Object object) {
		write(Logger.DEBUG, object);
	}
	
	public void trace(Object object) {
		write(Logger.TRACE, object);
	}

	
	
	
	
	
	public void fatal(String logger, Object param) {
		write(Logger.FATAL, new StringFormat(logger).format(param));
	}
	
	public void error(String logger, Object param) {
		write(Logger.ERROR, new StringFormat(logger).format(param));
	}
	
	public void warn(String logger, Object param) {
		write(Logger.WARN, new StringFormat(logger).format(param));
	}
	
	public void info(String logger, Object param) {
		write(Logger.INFO, new StringFormat(logger).format(param));
	}
	
	public void debug(String logger, Object param) {
		write(Logger.DEBUG, new StringFormat(logger).format(param));
	}
	
	public void trace(String logger, Object param) {
		write(Logger.TRACE, new StringFormat(logger).format(param));
	}
	
	
	
	
	
	public void fatal(String logger, Object[] params) {
		write(Logger.FATAL, new StringFormat(logger).format(params));
	}
	
	public void error(String logger, Object[] params) {
		write(Logger.ERROR, new StringFormat(logger).format(params));
	}
	
	public void warn(String logger, Object[] params) {
		write(Logger.WARN, new StringFormat(logger).format(params));
	}
	
	public void info(String logger, Object[] params) {
		write(Logger.INFO, new StringFormat(logger).format(params));
	}
	
	public void debug(String logger, Object[] params) {
		write(Logger.DEBUG, new StringFormat(logger).format(params));
	}
	
	public void trace(String logger, Object[] params) {
		write(Logger.TRACE, new StringFormat(logger).format(params));
	}

}
