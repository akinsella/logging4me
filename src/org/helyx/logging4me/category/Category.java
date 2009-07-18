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
package org.helyx.logging4me.category;

import java.util.Enumeration;
import java.util.Vector;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LogEvent;
import org.helyx.logging4me.appender.Appender;

public class Category {
	
	private String name;

	private int level;

	protected Vector appenderList;
	
	private Category parent;
	
	private boolean additive = true;

	public Category(Class _class, int level, boolean additive) {
		super();
		this.name = _class.getName();
		this.level = level;
		this.additive = additive;
	}

	public Category(String name, int level, boolean additive) {
		super();
		this.name = name;
		this.level = level;
		this.additive = additive;
	}

	public Category(Class _class, int level) {
		super();
		this.name = _class.getName();
		this.level = level;
	}

	public Category(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isAdditive() {
		return additive;
	}

	public void setAdditive(boolean additive) {
		this.additive = additive;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public void addAppender(Appender appender) {
		if (appenderList == null) {
			appenderList = new Vector();
		}
		appenderList.addElement(appender);
	}
	
	public void removeAppender(Appender appender) {
		appenderList.removeElement(appender);
	}
	
	public void removeAllAppenders() {
		appenderList.removeAllElements();
	}
	
	public boolean isLoggable(int level) {
		return level >= this.level;
	}
	
	public boolean isFatalEnabled() {
		return Logger.FATAL >= level;
	}
	
	public boolean isErrorEnabled() {
		return Logger.ERROR >= level;
	}
	
	public boolean isWarnEnabled() {
		return Logger.WARN >= level;
	}
	
	public boolean isInfoEnabled() {
		return Logger.INFO >= level;
	}
	
	public boolean isDebugEnabled() {
		return Logger.DEBUG >= level;
	}
	
	public boolean isTraceEnabled() {
		return Logger.TRACE >= level;
	}

	public void flushLogEventToAppenders(LogEvent logEvent) {
		
		if (appenderList != null) {
			Enumeration _enum = appenderList.elements();
			while (_enum.hasMoreElements()) {
				Appender appender = (Appender)_enum.nextElement();
				appender.write(logEvent);
			}
		}
		
		if (additive && parent != null) {
			parent.flushLogEventToAppenders(logEvent);
		}
	}
	
	public void dispose() {
		parent = null;
		if (appenderList != null) {
			appenderList.removeAllElements();
		}
		appenderList = null;
	}

}
