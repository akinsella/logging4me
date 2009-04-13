package org.helyx.logging4me.format.date;

import java.util.Calendar;
import java.util.Date;

import org.helyx.logging4me.format.number.NumberFormatUtil;

public class DateFormatUtil {
	
	private DateFormatUtil() {
		super();
	}

	public static String formatDatetime(Date date, String dateSeparator, String timeSeparator, String dateTimeSeparator) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = NumberFormatUtil.format(calendar.get(Calendar.YEAR), 4);
		String month = NumberFormatUtil.format(calendar.get(Calendar.MONTH) + 1, 2);
		String day = NumberFormatUtil.format(calendar.get(Calendar.DATE), 2);
		String hour = NumberFormatUtil.format(calendar.get(Calendar.HOUR_OF_DAY), 2);
		String minute = NumberFormatUtil.format(calendar.get(Calendar.MINUTE), 2);
		String second = NumberFormatUtil.format(calendar.get(Calendar.SECOND), 2);
		
		return day + dateSeparator + month + dateSeparator + year + dateTimeSeparator + hour + timeSeparator + minute + timeSeparator + second;
	}

	public static String formatDate(Date date) {
		return formatDate(date, "/");
	}

	public static String formatDate(Date date, String separator) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = NumberFormatUtil.format(calendar.get(Calendar.YEAR), 4);
		String month = NumberFormatUtil.format(calendar.get(Calendar.MONTH) + 1, 2);
		String day = NumberFormatUtil.format(calendar.get(Calendar.DATE), 2);
		
		return day + separator + month + separator + year;
	}

	public static String formatTime(Date date) {
		return formatTime(date, ":");
	}

	public static String formatTime(Date date, String separator) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String hour = NumberFormatUtil.format(calendar.get(Calendar.HOUR_OF_DAY), 2);
		String minute = NumberFormatUtil.format(calendar.get(Calendar.MINUTE), 2);
		String second = NumberFormatUtil.format(calendar.get(Calendar.SECOND), 2);
		
		return hour + separator + minute + separator + second;
	}
	
}
