package org.helyx.logging4me.format;

import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	
	private DateFormatUtil() {
		super();
	}

	public static String formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = NumberFormatUtil.format(calendar.get(Calendar.YEAR), 4);
		String month = NumberFormatUtil.format(calendar.get(Calendar.MONTH) + 1, 2);
		String day = NumberFormatUtil.format(calendar.get(Calendar.DATE), 2);
		String hour = NumberFormatUtil.format(calendar.get(Calendar.HOUR_OF_DAY), 2);
		String minute = NumberFormatUtil.format(calendar.get(Calendar.MINUTE), 2);
		String second = NumberFormatUtil.format(calendar.get(Calendar.SECOND), 2);
		
		return day + "/" + month + "/" + year + ", " + hour + ":" + minute + ":" + second;
	}
	
}
