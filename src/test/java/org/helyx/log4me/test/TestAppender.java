package org.helyx.log4me.test;

import java.util.Vector;

import org.helyx.logging4me.LoggerEvent;
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

	public void onWrite(LoggerEvent loggerEvent, String logMessage) {
		logInformationList.addElement(new LogInformation(loggerEvent, logMessage));
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

}
