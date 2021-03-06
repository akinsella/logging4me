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
package org.helyx.logging4me.appender;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LogEvent;

public class ConsoleAppender extends AbstractAppender {
	
	public static String CONSOLE_APPENDER_NAME = "CONSOLE";

	public ConsoleAppender() {
		super();
	}

	public void onWrite(LogEvent logEvent, String logMessage) {
		try {			
			if (logEvent.level == Logger.FATAL || logEvent.level == Logger.ERROR) {
				System.err.println(logMessage);
			}
			else {
				System.out.println(logMessage);
			}
		}
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	}

	public String getName() {
		return CONSOLE_APPENDER_NAME;
	}

	public boolean isOpened() {
		return true;
	}

}
