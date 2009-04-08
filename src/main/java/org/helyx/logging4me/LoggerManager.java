package org.helyx.logging4me;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.appender.ConsoleAppender;
import org.helyx.logging4me.appender.FileAppender;
import org.helyx.logging4me.category.Category;

public class LoggerManager {
	
	private static Hashtable appenderMap = new Hashtable();
	private static Vector appenderCacheList = null;
	
	private static Hashtable categoryMap = new Hashtable();
	
	private static int thresholdLevel = Logger.DEBUG;
	
	private static boolean debugMode = false;
	
	static {
		Appender consoleAppender = ConsoleAppender.getInstance();
		addAppender(consoleAppender);
	}
	
	private LoggerManager() {
		super();
	}
	
	public static void addAppender(Appender appender) {
		if (!appenderMap.contains(appender)) {
			appenderMap.put(appender.getName(), appender);
			appenderCacheList = null;
		}
		else {
			throw new LoggerException("Appender already added: '" + appender.getName() + "'");
		}
	}

	public static void addAppenderAndOpen(FileAppender appender) throws Exception {
		appender.open();
		addAppender(appender);
	}

	public static boolean existAppender(Appender appender) {
		return appenderMap.containsKey(appender.getName());
	}

	public static void removeAppender(Appender appender) {
		appenderMap.remove(appender);
		appenderCacheList = null;
	}

	public static void removeAppenderAndClose(Appender appender) {
		try {
			try {
				appender.flush();
			}
			finally {
				try {
					appender.close();
				}
				finally {
					removeAppender(appender);
				}
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}

	public static void flushAppenders() {
		Enumeration _enum = appenderMap.elements();
		
		while (_enum.hasMoreElements()) {			
			try {
				Appender appender = (Appender)_enum.nextElement();
				appender.flush();
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}

	public static void closeAllAppenders() {
		Enumeration _enum = appenderMap.elements();
		while (_enum.hasMoreElements()) {			
			Appender appender = (Appender)_enum.nextElement();
			try {
				try {
					appender.flush();
				}
				finally {
					appender.close();
				}
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
		
		appenderMap.clear();
		appenderCacheList = null;
	}
	
	public static void removeAllCateogries() {
		categoryMap.clear();
	}

	public static void setThresholdLevel(int thresholdLevel) {
		LoggerManager.thresholdLevel = thresholdLevel;
	}

	public static int getThresholdLevel() {
		return thresholdLevel;
	}

	public static void addCategory(String categoryName, String appenderName, int level) {
		Category category = new Category(categoryName, appenderName, level);
		categoryMap.put(categoryName, category);			
	}

	public static void addCategory(String categoryName, int level) {
		addCategory(categoryName, level);
	}

	public static Category getCategory(String categoryName) {
		Category category = (Category)categoryMap.get(categoryName);
		
		return category;
	}
	
	
	
	
	public static boolean isLoggable(int level) {
		return level >= thresholdLevel;
	}
	
	public static boolean isFatalEnabled() {
		return Logger.FATAL >= thresholdLevel;
	}
	
	public static boolean isErrorEnabled() {
		return Logger.ERROR >= thresholdLevel;
	}
	
	public static boolean isWarnEnabled() {
		return Logger.WARN >= thresholdLevel;
	}
	
	public static boolean isInfoEnabled() {
		return Logger.INFO >= thresholdLevel;
	}
	
	public static boolean isDebugEnabled() {
		return Logger.DEBUG >= thresholdLevel;
	}
	
	public static boolean isTraceEnabled() {
		return Logger.TRACE >= thresholdLevel;
	}

	public static Appender getAppender(String appenderName) {
		return (Appender)appenderMap.get(appenderName);
	}

	public static void clearCacheAppenderResolution() {
		if (debugMode) {
			System.out.println("LoggerManager.clearCacheAppenderResolution()");
		}
		Enumeration _enum = appenderMap.elements();
		while(_enum.hasMoreElements()) {
			Category category = (Category)_enum.nextElement();
			category.clearCacheAppenderResolution();
		}
	}

	public static Vector getAppenderCacheList() {
		if (appenderCacheList != null) {
			return appenderCacheList;
		}
		Vector tmpAppenderCacheList = new Vector();
		Enumeration _enum = appenderMap.elements();
		while(_enum.hasMoreElements()) {
			String appenderName = (String)_enum.nextElement();
			tmpAppenderCacheList.addElement(LoggerManager.getAppender(appenderName));
		}
		appenderCacheList = tmpAppenderCacheList;
		
		return appenderCacheList;
	}

	public static boolean isDebugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean debugMode) {
		LoggerManager.debugMode = debugMode;
	}

}
