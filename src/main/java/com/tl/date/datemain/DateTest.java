package com.tl.date.datemain;

import java.util.Date;

import com.tl.date.util.DateUtil;

public class DateTest {

	public static void main(String[] args) {
		Date currDate = new Date();
		System.out.println(currDate);
		System.out.println(DateUtil.getFirstAuditTime(currDate));
	}

}
