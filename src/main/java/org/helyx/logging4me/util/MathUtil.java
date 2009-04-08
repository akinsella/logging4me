package org.helyx.logging4me.util;

public class MathUtil {

	private MathUtil() {
		super();
	}
	
	public static long pow(long value, long pow) {
		long tmpValue = 1;
		
		for (int i = 0 ; i < pow ; i++) {
			tmpValue *= value;
		}
		
		return tmpValue;
	}
	
}
