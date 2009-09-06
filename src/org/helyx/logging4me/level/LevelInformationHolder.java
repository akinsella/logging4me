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
package org.helyx.logging4me.level;

import java.util.Enumeration;
import java.util.Hashtable;

import org.helyx.logging4me.LoggerManager;
import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.category.Category;

public class LevelInformationHolder {

	private int loggerManagerThresholdLevel;
	private Hashtable categoryMap = new Hashtable();
	private Hashtable appenderMap = new Hashtable();

	private LevelInformationHolder() {
		super();
		
	}
	
	public int getLoggerManagerThresholdLevel() {
		return loggerManagerThresholdLevel;
	}
	
	public Hashtable getCategoryMap() {
		return categoryMap;
	}
	
	public Hashtable getAppenderMap() {
		return appenderMap;
	}

	public void setLoggerManagerThresholdLevel(int loggerManagerThresholdLevel) {
		this.loggerManagerThresholdLevel = loggerManagerThresholdLevel;
	}

	public static LevelInformationHolder backupLevelInformations() {
		LevelInformationHolder levelInformationHolder = new LevelInformationHolder();
		
		levelInformationHolder.loggerManagerThresholdLevel = LoggerManager.getThresholdLevel();
		Enumeration enumCategory = LoggerManager.getCategoryList().elements();
		while (enumCategory.hasMoreElements()) {
			Category category = (Category)enumCategory.nextElement();
			levelInformationHolder.categoryMap.put(category.getName(), new Integer(category.getLevel()));
		}
		Enumeration enumAppender = LoggerManager.getAppenderList().elements();
		while (enumAppender.hasMoreElements()) {
			Appender appender = (Appender)enumAppender.nextElement();
			levelInformationHolder.categoryMap.put(appender.getName(), new Integer(appender.getThresholdLevel()));
		}
		
		return levelInformationHolder;
	}
	
	public void dispose() {
		if (categoryMap != null) {
			categoryMap.clear();
		}
		if (appenderMap != null) {
			appenderMap.clear();
		}
	}
	
}
