package org.helyx.logging4me;

public class UnknownLoggerLevelException extends RuntimeException {

	public UnknownLoggerLevelException() {
		super();
	}

	public UnknownLoggerLevelException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownLoggerLevelException(String message) {
		super(message);
	}

	public UnknownLoggerLevelException(Throwable cause) {
		super(cause);
	}

}
