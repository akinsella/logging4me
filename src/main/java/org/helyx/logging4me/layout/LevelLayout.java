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
package org.helyx.logging4me.layout;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerEvent;

public class LevelLayout implements Layout {

	private String separator = " - ";
	
	public LevelLayout() {
		super();
	}
	
	public LevelLayout(String separator) {
		super();
		this.separator = separator;
	}

	public String format(LoggerEvent loggerEvent) {
		StringBuffer sb = new StringBuffer().append("[").append(Logger.getLevelName(loggerEvent.level)).append("] ").append(loggerEvent.message);
		
		return sb.toString();
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
