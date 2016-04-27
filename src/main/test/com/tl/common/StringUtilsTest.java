package com.tl.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testIsBlank() {
		assertEquals(true, StringUtils.isBlank(null));
		assertEquals(true, StringUtils.isBlank(""));
		assertEquals(true, StringUtils.isBlank("   "));
		assertEquals(false, StringUtils.isBlank("a"));
		assertEquals(false, StringUtils.isBlank("null"));
	}

}
