package org.helyx.logging4me.layout;

import java.util.Date;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.format.date.DateFormatUtil;

public class SimpleLayout implements Layout {

	private String separator = " - ";
	
	public SimpleLayout() {
		super();
	}
	
	public SimpleLayout(String separator) {
		super();
		this.separator = separator;
	}

	public String format(int level, String category, String message, Date date) {
		String dateStr = DateFormatUtil.formatDate(date);
		
		StringBuffer sb = new StringBuffer().append("[").append(Logger.getLevelName(level)).append(" - ").append(category).append(" - ").append(dateStr).append("] ").append(message);
		
		return sb.toString();
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
