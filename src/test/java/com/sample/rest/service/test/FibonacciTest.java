package com.sample.rest.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import com.sample.rest.entity.binding.FibonacciBean;
import com.sample.rest.entity.binding.Value;
import com.sample.rest.service.fibonacci.impl.FibonacciServiceIterativeImpl;

public class FibonacciTest {

	private static FibonacciServiceIterativeImpl fibIt = null;

	@Test
	public void setup() {
		fibIt = new FibonacciServiceIterativeImpl();
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
		
		FibonacciBean fib = fibIt.getFibonacci(5);
		Iterator<Value> valueIterator = fib.getValue().iterator();
		for (int i = 0; i < 5; i++) {
			Value value = (Value)valueIterator.next();
			assertEquals(String.valueOf(i), value.getIndex());
			assertEquals(String.valueOf(cases[i]),value.getText());
		}
	}



}
