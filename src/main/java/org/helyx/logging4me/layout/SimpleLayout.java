package org.helyx.logging4me.layout;

import java.util.Date;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.format.DateFormatUtil;

public class SimpleLayout implements Layout {

	public String format(int level, String category, String message, Date date) {
		String dateStr = DateFormatUtil.formatDate(date);
		
		StringBuffer sb = new StringBuffer().append("[").append(Logger.getLevelName(level)).append(" - ").append(category).append(" - ").append(dateStr).append("] ").append(message);
		
		return sb.toString();
	}

}
