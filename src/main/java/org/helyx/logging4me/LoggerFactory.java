package org.helyx.logging4me;

public class LoggerFactory {
	
	private LoggerFactory() {
		super();
	}
	
	public static Logger getLogger(String category) {
		Logger logger = LoggerRepository.getLogger(category);
		
		return logger;
	}

}
