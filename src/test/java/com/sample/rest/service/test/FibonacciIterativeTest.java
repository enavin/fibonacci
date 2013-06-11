package com.sample.rest.service.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.TreeMap;

import org.junit.Test;

import com.sample.rest.entity.binding.FibonacciBean;
import com.sample.rest.service.fibonacci.impl.FibonacciServiceIterativeImpl;

public class FibonacciIterativeTest {

	@Test
	public void setup(){
		// setup proxy or other perdefined data here...
	}
	
	@Test
	public void testFibonacciIterative() {

		FibonacciServiceIterativeImpl fibIt = new FibonacciServiceIterativeImpl();
		FibonacciBean fb = fibIt.getFibonacci(5);
		assertNotNull(fb);
		
	}
}
