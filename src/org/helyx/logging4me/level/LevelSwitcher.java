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


public class LevelSwitcher {

	private LevelSwitcher() {
		super();
		
	}
	
	public static LevelInformationHolder getLevelInformations() {
		LevelInformationHolder levelInformationHolder = LevelInformationHolder.backupLevelInformations();
		
		return levelInformationHolder;
	}
	
	public static void switchToLevel(int targetLevel) {
		LoggerManager.setThresholdLevel(targetLevel);
		Enumeration enumCategory = LoggerManager.getCategoryList().elements();
		while (enumCategory.hasMoreElements()) {
			Category category = (Category)enumCategory.nextElement();
			category.setLevel(targetLevel);
		}
		Enumeration enumAppender = LoggerManager.getAppenderList().elements();
		while (enumAppender.hasMoreElements()) {
			Appender appender = (Appender)enumAppender.nextElement();
			appender.setThresholdLevel(targetLevel);
		}
	}
	
	public static void restoreLevel(LevelInformationHolder levelInformationHolder) {
		LoggerManager.setThresholdLevel(levelInformationHolder.getLoggerManagerThresholdLevel());
		Hashtable categoryMap = levelInformationHolder.getCategoryMap();
		Enumeration enumCategory = categoryMap.keys();
		while (enumCategory.hasMoreElements()) {
			String categoryName = (String)enumCategory.nextElement();
			Category category = LoggerManager.getCategory(categoryName);
			int categoryLevel = ((Integer)categoryMap.get(categoryName)).intValue();
			category.setLevel(categoryLevel);
		}
		Hashtable appenderMap = levelInformationHolder.getAppenderMap();
		Enumeration enumAppender = appenderMap.keys();
		while (enumAppender.hasMoreElements()) {
			String appenderName = (String)enumAppender.nextElement();
			Appender appender = LoggerManager.getAppender(appenderName);
			int appenderThresholdLevel = ((Integer)appenderMap.get(appenderName)).intValue();
			appender.setThresholdLevel(appenderThresholdLevel);
		}
	}
	
}
