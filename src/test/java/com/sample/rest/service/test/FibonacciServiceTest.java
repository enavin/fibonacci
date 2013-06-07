package com.sample.rest.service.test;

import static org.junit.Assert.assertEquals;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.sample.rest.common.TestHelper;
import com.sample.rest.entity.binding.Fibonacci;
import com.sample.rest.service.fibonacci.FibonacciService;
import com.sample.rest.service.fibonacci.impl.FibonacciServiceImpl;

public class FibonacciServiceTest {

	@Test
	public void setup(){
		// setup proxy or other perdefined data here...
	}
	
	@Test
	public void testGetFibonacci() {

		FibonacciService fibonacciService = new FibonacciServiceImpl();
		Fibonacci actualFibonacci = fibonacciService.getFibonacci(5);
		
		JSONObject actualJson = JSONObject.fromObject(actualFibonacci);
		
		assertEquals(TestHelper.getTestDataAsJSON(),actualJson);
		
	}	
	

}

