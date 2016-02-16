package com.tl.jdktest.numberic;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DataFormat {

	public static void main(String[] args) {
		System.out.println(String.format("%.2f", 12.12512121d));
		
		DecimalFormat df = new DecimalFormat("#0.00");
		df.setRoundingMode(RoundingMode.FLOOR);
		System.out.println(df.format(12.34699));
	}

}
