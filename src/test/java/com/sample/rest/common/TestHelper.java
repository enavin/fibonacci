package com.sample.rest.common;

import com.sample.rest.entity.binding.Fibonacci;

public class TestHelper {

	public static Fibonacci getTestData(){
		Fibonacci expectedFibonacci = new Fibonacci();
		expectedFibonacci.addValue("0","0");
		expectedFibonacci.addValue("1","1");
		expectedFibonacci.addValue("2","1");
		expectedFibonacci.addValue("3","2");
		expectedFibonacci.addValue("4","3");
		return expectedFibonacci;
	}
}
