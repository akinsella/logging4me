package org.helyx.logging4me.format.number;

import org.helyx.logging4me.util.MathUtil;

public class NumberFormatUtil {

	private NumberFormatUtil() {
		super();
	}
	
	public static String format(long value, int digits) {

		if (digits < 2) {
			return String.valueOf(value);
		}

		if (value == 0) {
			StringBuffer sb = new StringBuffer();
			for (long i = 1 ; i < digits ; i++) {
				sb.append("0");
			}
			sb.append(value);
			String result = sb.toString();
			
			return result;
		}
		
		long maxValue = MathUtil.pow((long)10, (long)digits);
		
		if (value >= maxValue) {
			String result = String.valueOf(value);
			
			return result;
		}
		
		StringBuffer sb = new StringBuffer();
		for (long i = value * 10 ; i < maxValue ; i *= 10) {
			sb.append("0");
		}
		sb.append(value);
		String result = sb.toString();
		
		return result;
	}
	
}
