package com.tl.date.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {

	/**
	 * get the time between 10 and 16 o'clock on tomorrow
	 * 
	 * @param time
	 * @return
	 */
	public static Date getFirstAuditTime(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		int hourAndMinutes[] = getHMBetween10and16();
		calendar.set(Calendar.HOUR_OF_DAY, hourAndMinutes[0]);
		calendar.set(Calendar.MINUTE, hourAndMinutes[1]);
		return calendar.getTime();
	}

	private static int[] getHMBetween10and16() {
		int hourAndMinutes[] = new int[2];
		Random rand = new Random();
		hourAndMinutes[0] = 10 + rand.nextInt(7); // hour
		hourAndMinutes[1] = rand.nextInt(60);
		return hourAndMinutes;
	}
}
