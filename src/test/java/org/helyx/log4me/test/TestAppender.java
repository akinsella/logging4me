package org.helyx.log4me.test;

import java.util.Date;
import java.util.Vector;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.appender.AbstractAppender;
import org.helyx.logging4me.appender.LogInformation;

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

	public void onWrite(int level, Logger logger, String message, Date date, String logMessage) {
		logInformationList.addElement(new LogInformation(level, logger, message, date, logMessage));
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

}
