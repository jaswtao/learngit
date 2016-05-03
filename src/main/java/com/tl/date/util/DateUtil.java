package com.tl.date.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	
	public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	
	/**
	 * 获取给定日期的开始时间对象，即当天的00点00分00秒
	 * @param date
	 * @return
	 */
	public static Date toStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONDAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 0, 0, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取给定日期的最后时间对象，即当天的23点59分59秒
	 * @param date
	 * @return
	 */
	public static Date toEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONDAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}
	
	/**
	 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}
}
