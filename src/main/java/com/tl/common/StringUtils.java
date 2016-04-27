package com.tl.common;

public class StringUtils {

	/**
	 * to check a String whether a blank
	 * @param target
	 * @return
	 */
	public static boolean isBlank(String target) {
		if (target == null) {
			return true;
		}
		return target.trim().length() == 0;
	}
}
