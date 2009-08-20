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
package org.helyx.logging4me.system;

import org.helyx.logging4me.LoggerManager;

public class SystemLogger {
	
	private static final String PREFIX_DEBUG = "Logging4ME|DEBUG|";
	private static final String PREFIX_WARN = "Logging4ME|WARN|";
	private static final String PREFIX_ERROR = "Logging4ME|ERROR|";
	
	private SystemLogger() {
		super();
	}

	public static void debug(String category, String message) {
		if (LoggerManager.isDebugMode()) {
			System.out.println(PREFIX_DEBUG + category + "| " + message);
		}
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
