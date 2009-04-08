package org.helyx.logging4me;

public class LoggerException extends RuntimeException {

	public LoggerException() {
		super();
		
	}

	public LoggerException(String message) {
		super(message);
	}

	public LoggerException(Throwable throwable) {
		super(throwable.getMessage());
	}

}
