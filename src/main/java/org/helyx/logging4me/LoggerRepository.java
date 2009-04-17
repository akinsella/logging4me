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

import java.util.Hashtable;

import org.helyx.logging4me.category.Category;

public class LoggerRepository {
	
	private static Hashtable loggerMap = new Hashtable();

	private LoggerRepository() {
		super();
	}
	
	public static Logger getLogger(String categoryName) {

		Logger logger = null;
		if (loggerMap.containsKey(categoryName)) {
			logger = (Logger)loggerMap.get(categoryName);
		}
		else {
			Category category = LoggerManager.getCategory(categoryName);
			logger = new Logger(category);
			loggerMap.put(category, logger);
		}
		
		return logger;
	}
	
	public static void clear() {
		loggerMap.clear();
	}

}
