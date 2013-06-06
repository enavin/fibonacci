package com.sample.rest.service.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.TreeMap;

import org.junit.Test;

import com.sample.rest.service.fibonacci.impl.FibonacciIterative;

public class FibonacciIterativeTest {

	@Test
	public void setup(){
		// setup proxy or other perdefined data here...
	}
	
	@Test
	public void testFibonacciIterative() {

		FibonacciIterative fibIt = new FibonacciIterative();
		TreeMap<String,BigInteger> fibCache = fibIt.getFibonacci(5);
		assertNotNull(fibCache);
		
	}
}