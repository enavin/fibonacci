package com.sample.rest.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.TreeMap;

import org.junit.Test;

import com.sample.rest.service.fibonacci.impl.FibonacciIterative;

public class FibonacciTest {

	private static FibonacciIterative fibIt = null;

	@Test
	public void setup() {
		fibIt = new FibonacciIterative();
	}

	/**
	 * Tests that Fibonacci throws an IllegalArgumentException for a negative
	 * number.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void expectedIllegalArgumentException() {
		fibIt.getFibonacci(-1);
	}

	/**
	 * Tests that Fibonacci throws no IllegalArgumentException for zero or for a
	 * positive number.
	 */
	//@Test
	public void testThrowsIllegalArgumentException() {
		// test 0
		try {
			fibIt.getFibonacci(0);
		} catch (IllegalArgumentException e) {
			fail("Threw IllegalArgumentException for 0 but 0 is nonnegative: "
					+ e);
		}

		// test 1
		try {
			fibIt.getFibonacci(1);
		} catch (IllegalArgumentException e) {
			fail("Threw IllegalArgumentException for 1 but 1 is nonnegative: "
					+ e);
		}
	}

	/**
	 * Tests to see that Fibonacci returns the correct values,n=5
	 * 
	 */
	@Test
	public void testIntialSetofFive() {
		int[] cases = new int[] {0,1,1,2,3};
		
		TreeMap<String, BigInteger> fib = fibIt.getFibonacci(5);
		for (int i = 0; i < 5; i++) {
			assertEquals(cases[i],fib.get(Integer.toString(i)).intValue());
		}
	}



}
