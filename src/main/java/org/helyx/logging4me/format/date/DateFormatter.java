/**
 * Copyright (C) 2007-2009 Alexis Kinsella - $ {website} - <Helyx.org>
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
package org.helyx.logging4me.format.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import org.helyx.logging4me.format.number.NumberFormatUtil;

public class DateFormatter {

	private  static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd, HH:mm:ss";
	
	private static final String YYYY = "yyyy";
	private static final String YY = "yy";
	
	private static final String MM = "MM";
	private static final String M = "M";
	
	private static final String DD = "dd";
	private static final String D = "d";
	
	private static final String HH24 = "HH";
	private static final String H24 = "H";
	private static final String HH12 = "hh";
	private static final String H12 = "h";
	
	private static final String MII = "mm";
	private static final String MI = "m";
	
	private static final String SS = "SS";
	private static final String S = "s";

	private static final String Z = "Z";

	private static final String ZZ = "ZZ";
	
	private String dateFormat = DEFAULT_DATE_FORMAT;
	private Object[] tokens;
	
	private static Hashtable tokenMap;
	
	static {
		
		tokenMap = new Hashtable();
		
		tokenMap.put(YYYY, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int year = calendar.get(Calendar.YEAR);
				
				return String.valueOf(year);
			}
			
		});
		
		tokenMap.put(YY, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int year = calendar.get(Calendar.YEAR);
				
				return String.valueOf(year).substring(2);
			}
			
		});
		
		tokenMap.put(MM, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int month = calendar.get(Calendar.MONTH);

				return NumberFormatUtil.format(month, 2);
			}
			
		});
		
		tokenMap.put(M, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int month = calendar.get(Calendar.MONTH);

				return String.valueOf(month);
			}
			
		});
		
		tokenMap.put(DD, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int month = calendar.get(Calendar.DATE);

				return NumberFormatUtil.format(month, 2);
			}
			
		});
		
		tokenMap.put(D, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int day = calendar.get(Calendar.DATE);

				return String.valueOf(day);
			}
			
		});
		
		tokenMap.put(HH24, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int hour = calendar.get(Calendar.HOUR_OF_DAY);

				return NumberFormatUtil.format(hour, 2);
			}
			
		});
		
		tokenMap.put(H24, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int hour = calendar.get(Calendar.HOUR_OF_DAY);

				return String.valueOf(hour);
			}
			
		});
		
		tokenMap.put(HH12, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int hour = calendar.get(Calendar.HOUR);

				return NumberFormatUtil.format(hour, 2);
			}
			
		});
		
		tokenMap.put(H12, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int hour = calendar.get(Calendar.HOUR);

				return String.valueOf(hour);
			}
			
		});

		tokenMap.put(MII, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int minute = calendar.get(Calendar.MINUTE);

				return NumberFormatUtil.format(minute, 2);
			}
			
		});
		
		tokenMap.put(MI, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int minute = calendar.get(Calendar.MINUTE);

				return String.valueOf(minute);
			}
			
		});

		tokenMap.put(SS, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int second = calendar.get(Calendar.SECOND);

				return NumberFormatUtil.format(second, 2);
			}
			
		});
		
		tokenMap.put(S, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int second = calendar.get(Calendar.SECOND);

				return String.valueOf(second);
			}
			
		});

		tokenMap.put(ZZ, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int milliSeconds = calendar.get(Calendar.MILLISECOND);

				return NumberFormatUtil.format(milliSeconds, 3);
			}
			
		});

		tokenMap.put(Z, new DatePartFormatter() {

			public String getValue(Calendar calendar) {
				int milliSeconds = calendar.get(Calendar.MILLISECOND);

				return String.valueOf(milliSeconds);
			}
			
		});
		
	}
	
	public DateFormatter() {
		super();
		tokenizeDateFormat();
	}

	public DateFormatter(String dateFormat) {
		super();
		this.dateFormat = dateFormat;
		tokenizeDateFormat();
	}
	
	private void tokenizeDateFormat() {
		Vector tokenList = new Vector();
		char[] chars = dateFormat.toCharArray();
		int length = chars.length;
		int i = 0;
		while (i < length) {
			char c = chars[i];
			char c1 = i + 1 < length ? chars[i + 1] : 0;
			char c2 = i + 2 < length ? chars[i + 2] : 0;
			char c3 = i + 3 < length ? chars[i + 3] : 0;
			if (c == 'y') {
				if (c1 == 'y') {
					if (c2 == 'y') {
						if (c3 == 'y') {
							tokenList.addElement(tokenMap.get(YYYY));
							i += 4;
						}
						else {
							tokenList.addElement(tokenMap.get(YY));
							i += 2;
						}						
					}
					else {
						tokenList.addElement(tokenMap.get(YY));
						i += 2;
					}
				}
				else {
					tokenList.addElement("y");
					i++;
				}
			}
			else if (c == 'M') {
				if (c1 == 'M') {
					tokenList.addElement(tokenMap.get(MM));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(M));
					i++;					
				}
			}
			else if (c == 'd') {
				if (c1 == 'd') {
					tokenList.addElement(tokenMap.get(DD));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(D));
					i++;					
				}
			}
			else if (c == 'H') {
				if (c1 == 'H') {
					tokenList.addElement(tokenMap.get(HH24));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(H24));
					i++;					
				}
			}
			else if (c == 'h') {
				if (c1 == 'h') {
					tokenList.addElement(tokenMap.get(HH12));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(H12));
					i++;					
				}
			}
			else if (c == 'm') {
				if (c1 == 'm') {
					tokenList.addElement(tokenMap.get(MII));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(MI));
					i++;					
				}
			}
			else if (c == 's') {
				if (c1 == 's') {
					tokenList.addElement(tokenMap.get(SS));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(S));
					i++;					
				}
			}
			else if (c == 'Z') {
				if (c1 == 'Z') {
					tokenList.addElement(tokenMap.get(ZZ));
					i += 2;
				}
				else {
					tokenList.addElement(tokenMap.get(Z));
					i++;
				}
			}
			else {
				tokenList.addElement(String.valueOf(c));
				i++;
			}
		}
		
		tokens = new Object[tokenList.size()];
		tokenList.copyInto(tokens);
	}
	
	public String format(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return format(calendar);
	}
	
	public String format(Calendar calendar) {
		StringBuffer sb = new StringBuffer(32);
		int length = tokens.length;
		for (int i = 0 ; i < length ; i++) {
			Object token = tokens[i]; 
			if (token instanceof DatePartFormatter) {
				DatePartFormatter datePartFormatter = (DatePartFormatter)token;
				String value = datePartFormatter.getValue(calendar);
				sb.append(value);
			}
			else {
				sb.append(token);
			}
		}
		String result = sb.toString();
		
		return result;
	}

}
