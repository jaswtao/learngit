package com.tl.jdktest.numberic;

import java.math.BigDecimal;

public class BigDecimalTest {

	public static void main(String[] args) {
		BigDecimal bFloat = new BigDecimal(0.16);
		bFloat.setScale(2);
		BigDecimal bInt = new BigDecimal(100);
		System.out.println(bFloat.multiply(bInt).toString());
	}

}
