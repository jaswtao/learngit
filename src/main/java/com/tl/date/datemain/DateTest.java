package com.tl.date.datemain;

import java.util.Date;

import com.tl.date.util.DateUtil;

public class DateTest {

	public static void main(String[] args) {
		Date currDate = new Date();
		System.out.println(currDate);
		System.out.println(DateUtil.getFirstAuditTime(currDate));
		
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date().getTime());
		
		System.out.println(DateUtil.dateToString(DateUtil.toStartOfDay(currDate)));
		System.out.println(DateUtil.dateToString(DateUtil.toEndOfDay(currDate)));
		System.out.println(DateUtil.toStartOfDay(currDate).getTime());
		System.out.println(DateUtil.toEndOfDay(currDate).getTime());
	}

}
