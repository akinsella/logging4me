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
package org.helyx.log4me.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerEvent;
import org.helyx.logging4me.category.Category;
import org.helyx.logging4me.layout.pattern.PatternLayout;

public class PatternLayoutTest extends TestCase {
	
	protected static final String TEST_CATEGORY = "TEST_CATEGORY";
	
	protected static final String TEST_CONTENT = "TEST_CONTENT";

	public void testPatternLayout() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 18);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 47);
		calendar.set(Calendar.SECOND, 56);
		calendar.set(Calendar.MILLISECOND, 9);
		
		String pattern = "| %T | %L | %C | %D{yyyy/MM/dd, HH:mm:ss.ZZ} | ";
		
//		Thread.currentThread().setName("MAIN");
		
		PatternLayout patternLayout = new PatternLayout(pattern);
		
		Logger logger = new Logger(new Category(TEST_CATEGORY, Logger.INFO));
		
		String patternFormatterResult = patternLayout.format(new LoggerEvent(Logger.INFO, logger, TEST_CONTENT, calendar.getTime()));
		
		System.out.println("Result: " + patternFormatterResult);
	}

}
