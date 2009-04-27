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

import org.helyx.logging4me.util.MathUtil;

public class MathUtilTest extends TestCase {

	public void testPow0() {
		assertEquals(1, MathUtil.pow((long)0, (long)0));
		assertEquals(1, MathUtil.pow((long)1, (long)0));
		assertEquals(1, MathUtil.pow((long)2, (long)0));
	}


	public void testPow1() {
		assertEquals(0, MathUtil.pow((long)0, (long)1));
		assertEquals(1, MathUtil.pow((long)1, (long)1));
		assertEquals(2, MathUtil.pow((long)2, (long)1));
	}

	public void testPow2() {
		assertEquals(0, MathUtil.pow((long)0, (long)2));
		assertEquals(1, MathUtil.pow((long)1, (long)2));
		assertEquals(4, MathUtil.pow((long)2, (long)2));
	}

	public void testPow10() {
		assertEquals(1, MathUtil.pow((long)10, (long)0));
		assertEquals(10, MathUtil.pow((long)10, (long)1));
		assertEquals(100, MathUtil.pow((long)10, (long)2));
	}

}
