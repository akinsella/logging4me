package org.helyx.logging4me.appender;

import java.util.Date;

import org.helyx.logging4me.Logger;


public interface Appender {
	
	String getName();

	void write(int level, Logger logger, String message, Date date);

	void open() throws Exception;

	void close() throws Exception;

	void flush() throws Exception;

	int getThresholdLevel();

	void setThresholdLevel(int thresholdLevel);
	
	boolean isLoggable(int level);
	
	boolean isFatalEnabled();
	
	boolean isErrorEnabled();
	
	boolean isWarnEnabled();
	
	boolean isInfoEnabled();
	
	boolean isDebugEnabled();
	
	boolean isTraceEnabled();
	
}
