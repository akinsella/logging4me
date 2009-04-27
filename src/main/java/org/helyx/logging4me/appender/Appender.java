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

import org.helyx.logging4me.LoggerEvent;
import org.helyx.logging4me.layout.Layout;


public interface Appender {
	
	String getName();

	void write(LoggerEvent loggerEvent);

	void open() throws Exception;

	void close() throws Exception;

	void flush() throws Exception;

	int getThresholdLevel();

	void setThresholdLevel(int thresholdLevel);

	Layout getLayout();
	
	void setLayout(Layout layout);
	
	boolean isLoggable(int level);
	
	boolean isFatalEnabled();
	
	boolean isErrorEnabled();
	
	boolean isWarnEnabled();
	
	boolean isInfoEnabled();
	
	boolean isDebugEnabled();
	
	boolean isTraceEnabled();
	
}
