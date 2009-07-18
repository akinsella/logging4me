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
import org.helyx.logging4me.layout.Layout;

public abstract class AbstractAppender implements Appender {

	private int thresholdLevel = Logger.DEBUG;
	
	private Layout layout;

	public AbstractAppender() {
		super();
	}

	public void write(LogEvent logEvent) {
		try {
			
			if(isLoggable(logEvent.level)) {
				String logMessage = formatLog(logEvent);
				onWrite(logEvent, logMessage);
			}
		}
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	}

	public abstract void onWrite(LogEvent logEvent, String logMessage);
	
	protected String formatLog(LogEvent logEvent) {
		if (layout == null) {
			return logEvent.message;
		}
		return layout.format(logEvent);
	}
	
	public int getThresholdLevel() {
		return thresholdLevel;
	}

	public void setThresholdLevel(int thresholdLevel) {
		this.thresholdLevel = thresholdLevel;
	}

	
	public boolean isLoggable(int level) {
		return level >= thresholdLevel;
	}
	
	public boolean isFatalEnabled() {
		return Logger.FATAL >= thresholdLevel;
	}
	
	public boolean isErrorEnabled() {
		return Logger.ERROR >= thresholdLevel;
	}
	
	public void close() throws Exception {
		// Do Nothing
	}

	public void flush() throws Exception {
		// Do Nothing
	}

	public void open() throws Exception {
		// Do Nothing
	}

	public boolean isWarnEnabled() {
		return Logger.WARN >= thresholdLevel;
	}
	
	public boolean isInfoEnabled() {
		return Logger.INFO >= thresholdLevel;
	}
	
	public boolean isDebugEnabled() {
		return Logger.DEBUG >= thresholdLevel;
	}
	
	public boolean isTraceEnabled() {
		return Logger.TRACE >= thresholdLevel;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
}
