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
package org.helyx.log4me.test;

import junit.framework.TestCase;

import org.helyx.logging4me.text.TextUtil;

public class TextUtilTest extends TestCase {

	public void testReplaceAllChar() {
		assertEquals("", TextUtil.replaceAll("", 'a', 'z'));
		assertEquals("z", TextUtil.replaceAll("a", 'a', 'z'));
		assertEquals("zz", TextUtil.replaceAll("aa", 'a', 'z'));
		assertEquals("zb", TextUtil.replaceAll("ab", 'a', 'z'));
		assertEquals("bz", TextUtil.replaceAll("ba", 'a', 'z'));
		assertEquals("zbz", TextUtil.replaceAll("aba", 'a', 'z'));
		assertEquals("zbc", TextUtil.replaceAll("abc", 'a', 'z'));
		assertEquals("bcd", TextUtil.replaceAll("bcd", 'a', 'z'));
	}
	
	public void testReplaceAllStringOneChar() {
		assertEquals("", TextUtil.replaceAll("", "a", "z"));
		assertEquals("z", TextUtil.replaceAll("a", "a", "z"));
		assertEquals("zz", TextUtil.replaceAll("aa", "a", "z"));
		assertEquals("zb", TextUtil.replaceAll("ab", "a", "z"));
		assertEquals("bz", TextUtil.replaceAll("ba", "a", "z"));
		assertEquals("zbz", TextUtil.replaceAll("aba", "a", "z"));
		assertEquals("zbc", TextUtil.replaceAll("abc", "a", "z"));
		assertEquals("bcd", TextUtil.replaceAll("bcd", "a", "z"));
	}
	
	public void testReplaceAllStringMultiChar() {
		assertEquals("", TextUtil.replaceAll("", "aa", "z"));
		assertEquals("a", TextUtil.replaceAll("a", "aa", "z"));
		assertEquals("zz", TextUtil.replaceAll("aa", "aa", "zz"));
		assertEquals("zzb", TextUtil.replaceAll("aab", "aa", "zz"));
		assertEquals("bzz", TextUtil.replaceAll("baa", "aa", "zz"));
		assertEquals("zzbzz", TextUtil.replaceAll("aabaa", "aa", "zz"));
		assertEquals("zzbc", TextUtil.replaceAll("aabc", "aa", "zz"));
		assertEquals("bcd", TextUtil.replaceAll("bcd", "aa", "zz"));
	}
	
	public void testReplaceAllStringEmpty() {
		assertEquals("", TextUtil.replaceAll("aa", "aa", ""));
		assertEquals("b", TextUtil.replaceAll("aab", "aa", ""));
		assertEquals("aa", TextUtil.replaceAll("aa", "", "b"));
		assertEquals("aa", TextUtil.replaceAll("aa", null, "b"));
		assertEquals("", TextUtil.replaceAll("aa", "aa", null));
	}	
}
