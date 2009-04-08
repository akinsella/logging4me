package org.helyx.logging4me.category;

import java.util.Enumeration;
import java.util.Vector;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;

public class Category {
	
	public String categoryName;
	
	public int level;
	
	private boolean wildCard = true;
	
	private Vector appenderNameList = new Vector();
	private Vector appenderCacheList = new Vector();
	

	public Category(String categoryName, String appenderName, int level) {
		super();
		this.categoryName = categoryName;
		this.level = level;
		addAppender(appenderName);
	}

	public Category(String categoryName, int level) {
		super();
		this.categoryName = categoryName;
		this.level = level;
	}
	
	public String getName() {
		return categoryName;
	}

	public void addAppender(String appenderName) {
		this.appenderNameList.addElement(appenderName);
		wildCard = false;
		clearCacheAppenderResolution();
	}
	
	public void removeAppender(String appenderName) {
		this.appenderNameList.removeElement(appenderName);
		if (appenderNameList.isEmpty()) {
			wildCard = true;
		}
		clearCacheAppenderResolution();
	}
	
	public void removeAllAppenders() {
		this.appenderNameList.removeAllElements();
		wildCard = true;
		clearCacheAppenderResolution();
	}

	public Vector getAppenderNameList() {
		return appenderNameList;
	}

	public Vector getAppenderCacheList() {
		if (wildCard) {
			return LoggerManager.getAppenderCacheList();
		}
		if (appenderCacheList == null) {
			appenderCacheList = new Vector();
			Enumeration _enum = appenderNameList.elements();
			while(_enum.hasMoreElements()) {
				String appenderName = (String)_enum.nextElement();
				appenderCacheList.addElement(LoggerManager.getAppender(appenderName));
			}
		}
		
		return appenderCacheList;
	}
	
	public boolean isLoggable(int level) {
		return level >= this.level;
	}
	
	public boolean isFatalEnabled() {
		return Logger.FATAL >= level;
	}
	
	public boolean isErrorEnabled() {
		return Logger.ERROR >= level;
	}
	
	public boolean isWarnEnabled() {
		return Logger.WARN >= level;
	}
	
	public boolean isInfoEnabled() {
		return Logger.INFO >= level;
	}
	
	public boolean isDebugEnabled() {
		return Logger.DEBUG >= level;
	}
	
	public boolean isTraceEnabled() {
		return Logger.TRACE >= level;
	}

	public boolean isWildCard() {
		return wildCard;
	}

	public void clearCacheAppenderResolution() {
		if (LoggerManager.isDebugMode()) {
			System.out.println("category[" + categoryName + "].clearCacheAppenderResolution()");
		}
		appenderCacheList.removeAllElements();
		appenderCacheList = null;
	}

}
