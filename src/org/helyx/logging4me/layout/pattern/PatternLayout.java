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
package org.helyx.logging4me.layout.pattern;

import java.util.Hashtable;
import java.util.Vector;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerEvent;
import org.helyx.logging4me.layout.Layout;
import org.helyx.logging4me.layout.PatternPartFormatter;

public class PatternLayout implements Layout {

	private String separator = " - ";
	
	public static final String DEFAULT_PATTERN = "| %T | %L | %C | %D{yyyy/MM/dd, HH:mm:ss.ZZ} | ";
	
	private String pattern = DEFAULT_PATTERN;

	private static final String THREAD = "T";
	private static final String DATE = "D";
	private static final String CATEGORY = "C";
	private static final String LEVEL = "L";

	
	private Object[] tokens;
	
	private static Hashtable tokenMap;

	static {
		
		tokenMap = new Hashtable();
		
		tokenMap.put(THREAD, new PatternPartFormatter() {

			public String getValue(Logger logger) {
				return Thread.currentThread().getName();
			}
			
		});
		
		tokenMap.put(LEVEL, new PatternPartFormatter() {

			public String getValue(Logger logger) {
				return Logger.getLevelName(logger.getCategory().getLevel());
			}
			
		});
		
		tokenMap.put(CATEGORY, new PatternPartFormatter() {

			public String getValue(Logger logger) {
				return logger.getCategory().getName();
			}
			
		});
	}
	
	public PatternLayout() {
		super();
		tokenizePattern();
	}
	
	public PatternLayout(String pattern) {
		super();
		this.pattern = pattern;
		tokenizePattern();
	}

	private void tokenizePattern() {
		Vector tokenList = new Vector();
		char[] chars = pattern.toCharArray();
		int length = chars.length;
		int i = 0;
		while (i < length) {
			char c = chars[i];
			char c1 = i + 1 < length ? chars[i + 1] : 0;
			char c2 = i + 2 < length ? chars[i + 2] : 0;
			if (c == '%') {
				if (c1 == 'T') {
					tokenList.addElement(tokenMap.get(THREAD));
					i += 2;
				}
				else if (c1 == 'C') {
					tokenList.addElement(tokenMap.get(CATEGORY));
					i += 2;
				}
				else if (c1 == 'L') {
					tokenList.addElement(tokenMap.get(LEVEL));
					i += 2;
				}
				else if (c1 == 'D') {
					if (c2 == '{') {
						int posEndBracket = pattern.indexOf('}', i);
						if (posEndBracket < 0) {
							tokenList.addElement("%D");
							i += 2;
						}
						else {
							String dateFormatStr = pattern.substring(i + 3, posEndBracket);
							tokenList.addElement(new DatePatternPartFormatter(dateFormatStr));
							i += posEndBracket - i + 1;
						}
					}
					else {
						tokenList.addElement("%D");
						i += 2;
					}
				}
				else {
					tokenList.addElement(String.valueOf(c));
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

	public String format(LoggerEvent loggerEvent) {
		StringBuffer sb = new StringBuffer(64);
		int length = tokens.length;
		for (int i = 0 ; i < length ; i++) {
			Object token = tokens[i]; 
			if (token instanceof PatternPartFormatter) {
				PatternPartFormatter patternPartFormatter = (PatternPartFormatter)token;
				String value = patternPartFormatter.getValue(loggerEvent.logger);
				sb.append(value);
			}
			else {
				sb.append(token);
			}
		}

		sb.append(loggerEvent.message);

		String result = sb.toString();
		
		return result;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
