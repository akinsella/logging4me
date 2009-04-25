package org.helyx.log4me.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.helyx.log4me.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(PatternLayoutTest.class);
		suite.addTestSuite(LevelLoggerTest.class);
		suite.addTestSuite(DateFormatterTest.class);
		//$JUnit-END$
		return suite;
	}

}
