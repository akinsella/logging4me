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

import java.util.Calendar;

import junit.framework.TestCase;

import org.helyx.logging4me.format.date.DateFormatter;

public class DateFormatterTest extends TestCase {


	public void testDateTimeFormat1() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 18);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 47);
		calendar.set(Calendar.SECOND, 56);
		calendar.set(Calendar.MILLISECOND, 9);

		DateFormatter dateFormatter = new DateFormatter("yyyy/MM/dd, HH:mm:ss.ZZ");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("2009/04/18, 23:47:56.009", dateFormatResult);
	}

	public void testDateTimeFormat2() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 18);
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		calendar.set(Calendar.MINUTE, 07);
		calendar.set(Calendar.SECOND, 06);
		calendar.set(Calendar.MILLISECOND, 9);

		DateFormatter dateFormatter = new DateFormatter("d/M/yy, H:m:s.Z");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("18/4/09, 14:7:6.9", dateFormatResult);
	}

	public void testDateFormatYear6() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("yyyyyy");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("200909", dateFormatResult);
	}

	public void testDateFormatYear5() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("yyyyy");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("2009y", dateFormatResult);
	}

	public void testDateFormatYear4() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("yyyy");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("2009", dateFormatResult);
	}

	public void testDateFormatYear3() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("yyy");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("09y", dateFormatResult);
	}

	public void testDateFormatYear2() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("yy");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("09", dateFormatResult);
	}

	public void testDateFormatYear1() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);

		DateFormatter dateFormatter = new DateFormatter("y");
		String dateFormatResult = dateFormatter.format(calendar);
		
		assertEquals("y", dateFormatResult);
	}
	
}
