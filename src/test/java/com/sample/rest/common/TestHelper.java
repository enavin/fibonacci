package com.sample.rest.common;

import net.sf.json.JSONObject;

import com.sample.rest.entity.binding.FibonacciBean;

public class TestHelper {

	public static FibonacciBean getTestData(){
		FibonacciBean expectedFibonacci = new FibonacciBean();
		expectedFibonacci.addValue("0","0");
		expectedFibonacci.addValue("1","1");
		expectedFibonacci.addValue("2","1");
		expectedFibonacci.addValue("3","2");
		expectedFibonacci.addValue("4","3");
		return expectedFibonacci;
	}
	
	public static JSONObject getTestDataAsJSON(){
		return JSONObject.fromObject(TestHelper.getTestData());
	}
}
