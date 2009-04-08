package org.helyx.logging4me.layout;

import java.util.Date;

public interface Layout {

	public String format(int level, String category, String message, Date date);
	
}
