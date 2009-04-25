/**
 * Copyright (C) 2007-2009 Alexis Kinsella - http://www.helyx.org - <Helyx.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helyx.logging4me.category;

import java.util.Enumeration;
import java.util.Vector;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerManager;

public class Category {
	
	public String name;

	public int level;
	
	private boolean wildCard = true;
	
	private Vector appenderNameList = new Vector();
	private Vector appenderCacheList = new Vector();
	

	public Category(Class _class, String appenderName, int level) {
		super();
		this.name = _class.getName();
		this.level = level;
		addAppender(appenderName);
	}

	public Category(String name, String appenderName, int level) {
		super();
		this.name = name;
		this.level = level;
		addAppender(appenderName);
	}

	public Category(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
			System.out.println("category[" + name + "].clearCacheAppenderResolution()");
		}
		appenderCacheList.removeAllElements();
		appenderCacheList = null;
	}

}
