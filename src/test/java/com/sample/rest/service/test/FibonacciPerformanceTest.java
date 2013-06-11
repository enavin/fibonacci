package com.sample.rest.service.test;

import org.junit.Test;

import com.sample.rest.service.fibonacci.impl.FibonacciIterative;

public class FibonacciPerformanceTest {


	@Test
	public void setup() {
	}

	/**
	 * Tests performance between Iterative and Cached rescursive based fidonacci series generator module
	 *
	 */
	@Test
	public void testFibonacciPerformance() {
		long startTime;
		int seqNumber = 500;

		startTime = System.nanoTime();
		FibonacciIterative fibIt = new FibonacciIterative();
		fibIt.getFibonacci(seqNumber);
		System.out.printf("Iterative Fib: %d ms%n",
				(System.nanoTime() - startTime) / 1000000);

	}

}
