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
