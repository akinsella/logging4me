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
package org.helyx.logging4me;

import java.util.Enumeration;
import java.util.Hashtable;

import org.helyx.logging4me.appender.Appender;
import org.helyx.logging4me.category.Category;
import org.helyx.logging4me.system.SystemLogger;

public class LoggerManager {
	
	private static final String CAT = "LOGGER_MANAGER";
	
	private static Hashtable appenderMap = new Hashtable();
	
	private static Hashtable categoryMap = new Hashtable();
	
	private static int thresholdLevel = Logger.DEBUG;
	
	private static boolean debugMode = false;
	
	private static Category rootCategory;
	
	
	private static Hashtable loggerMap = new Hashtable();
	
	static {
		initialize();
	}
	
	private LoggerManager() {
		super();
	}
	
	private static void initialize() {
		rootCategory = new Category("ROOT", Logger.DEBUG);
		categoryMap.put(rootCategory.getName(), rootCategory);
	}

	public static void removeAndFinalizeAllCateogries() {
		Enumeration _enum = categoryMap.elements();
		while(_enum.hasMoreElements()) {
			Category category = (Category)_enum.nextElement();
			category.dispose();
		}
		
		categoryMap.clear();
	}

	public static void setThresholdLevel(int thresholdLevel) {
		LoggerManager.thresholdLevel = thresholdLevel;
	}

	public static int getThresholdLevel() {
		return thresholdLevel;
	}
	
	public static void registerAppender(Appender appender) {
		if (!appenderMap.containsKey(appender.getName())) {
			if (!appender.isOpened()) {
				SystemLogger.warn(CAT, "Appender[" + appender.getName() + "] is not open!");
			}
			appenderMap.put(appender.getName(), appender);
		}
	}
	
	public static void unregisterAppender(Appender appender) {
		if (appenderMap.containsKey(appender.getName())) {
			appenderMap.remove(appender.getName());
		}
		Enumeration _enum = categoryMap.elements();
		
		while (_enum.hasMoreElements()) {
			Category category = (Category)_enum.nextElement();
			category.removeAppender(appender);
		}
	}

	
	public static Category addCategory(String categoryName, int level) {
		if (categoryMap.contains(categoryName)) {
			Category category = (Category)categoryMap.get(categoryName);
			category.setLevel(level);
			return category;
		}
		
		Category category = registerAndConfigureCategory(categoryName, level);
		return category;
	}

	public static Category addCategory(String categoryName) {
		if (categoryMap.contains(categoryName)) {
			Category category = (Category)categoryMap.get(categoryName);
			return category;
		}
		
		Category category = registerAndConfigureCategory(categoryName, Logger.TRACE);
		return category;
	}
	
	private static Category registerAndConfigureCategory(String categoryName, int level) {
		Category category = new Category(categoryName, level);
		categoryMap.put(categoryName, category);		
		configureParentCategory(category);
		return category;
	}

	private static void configureParentCategory(Category category) {
		String categoryName = category.getName();
		int categoryNameLength = categoryName.length();
		int pos = categoryName.lastIndexOf('.', categoryNameLength - 1);
		if (pos >= 0) {
			String parentCategoryName = categoryName.substring(0, pos);
			SystemLogger.debug(CAT, "Parent Category Name : " + parentCategoryName);
			
			Category parentCategory = getCategory(parentCategoryName);
			if (parentCategory != null) {
				return;
			}
			parentCategory = addCategory(parentCategoryName);
			category.setParent(parentCategory);
		}
		else {
			category.setParent(rootCategory);
		}
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

	public static boolean isDebugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean debugMode) {
		LoggerManager.debugMode = debugMode;
	}

	public static void resetLoggers() {
		loggerMap.clear();
	}

	public static void reset(boolean closeRegisteredAppenders) {
		
		removeAndFinalizeAllCateogries();
		if (closeRegisteredAppenders) {
			closeAllRegisteredAppenders();
		}
		removeAllRegisteredAppenders();
		initialize();
	}

	private static void removeAllRegisteredAppenders() {
		appenderMap.clear();
	}

	private static void closeAllRegisteredAppenders() {
		Enumeration _enum = appenderMap.elements();
		while(_enum.hasMoreElements()) {
			Appender appender = (Appender)_enum.nextElement();
			try {
				appender.close();
			}
			catch (Exception e) {
				SystemLogger.warn(CAT, e.getMessage(), e);
			}
		}
		
		appenderMap.clear();
	}

	public static Category getRootCategory() {
		return rootCategory;
	}

	public static Appender getAppender(String appenderName) {
		Appender appender = (Appender)appenderMap.get(appenderName);
		return appender;
	}

	public static Logger getLogger(Class _class) {
		return getLogger(_class.getName());
	}
	
	public static Logger getLogger(String categoryName) {

		Logger logger = null;
		if (loggerMap.containsKey(categoryName)) {
			logger = (Logger)loggerMap.get(categoryName);
		}
		else {
			Category category = addCategory(categoryName);
			logger = new Logger(category);
			loggerMap.put(categoryName, logger);
		}
		
		return logger;
	}

	public static void resetAll() {
		resetLoggers();
		reset(true);
	}

}
