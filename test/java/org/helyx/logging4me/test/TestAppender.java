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
package org.helyx.logging4me.test;

import java.util.Vector;

import org.helyx.logging4me.LogEvent;
import org.helyx.logging4me.appender.AbstractAppender;

public class TestAppender extends AbstractAppender {

	private String name = "TEST_APPENDER";
	
	private Vector logInformationList = new Vector();

	public TestAppender() {
		super();
	}
	
	public TestAppender(String name) {
		super();
		this.name = name;
	}

	public void onWrite(LogEvent logEvent, String logMessage) {
		logInformationList.addElement(new LogInformation(logEvent, logMessage));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Vector getLogInformationList() {
		return logInformationList;
	}

	public void clearResults() {
		logInformationList.removeAllElements();
	}

	public boolean isOpened() {
		return true;
	}

}
