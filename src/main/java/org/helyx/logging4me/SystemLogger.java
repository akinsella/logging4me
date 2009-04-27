package org.helyx.logging4me;

public class SystemLogger {
	
	private static final String PREFIX_DEBUG = "Logging4ME|DEBUG|";
	private static final String PREFIX_WARN = "Logging4ME|WARN|";
	private static final String PREFIX_ERROR = "Logging4ME|ERROR|";
	
	private SystemLogger() {
		super();
	}

	public static void debug(String category, String message) {
		System.out.println(PREFIX_DEBUG + category + "| " + message);
	}

	public static void debug(String category, String message, Throwable t) {
		System.out.println(PREFIX_DEBUG + category + "| " + message);		
		System.out.println(PREFIX_DEBUG + category + "| " + t.getClass().getName() + "[" + t.getMessage() + "]");
		t.printStackTrace();
	}
	
	public static void warn(String category, String message) {
		System.err.println(PREFIX_WARN + category + "| " + message);
	}
	
	public static void error(String category, String message) {
		System.err.println(PREFIX_ERROR + category + "| " + message);
	}
	
	public static void warn(String category, String message, Throwable t) {
		System.err.println(PREFIX_WARN + category + "| " + message);		
		System.err.println(PREFIX_WARN + category + "| " + t.getClass().getName() + "[" + t.getMessage() + "]");
		t.printStackTrace();
	}
	
	public static void error(String category, String message, Throwable t) {
		System.err.println(PREFIX_ERROR + category + "| " + message);		
		System.err.println(PREFIX_ERROR + category + "| " + t.getClass().getName() + "[" + t.getMessage() + "]");
		t.printStackTrace();
	}
	
}
