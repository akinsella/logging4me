package org.helyx.log4me.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.helyx.logging4me.format.date.DateFormatter;

public class DateFormatterTest extends TestCase {


	public void testDateTimeFormat1() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2009);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 18);
		calendar.set(Calendar.HOUR, 23);
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
		calendar.set(Calendar.DATE, 18);
		calendar.set(Calendar.HOUR, 14);
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
