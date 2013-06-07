package com.sample.rest.service.fibonacci.impl;

import java.math.BigInteger;
import java.util.TreeMap;

/**
 * This class generates fibonacci using iterative model.
 * 
 * @author Naveen
 * 
 */
public class FibonacciIterative {

	/**
	 * Generates fibonacci series
	 * 
	 * @param seqNumber
	 *            the number of series that need to be generated
	 * 
	 * @return the fibonancci series object
	 */
	public TreeMap<String, BigInteger> getFibonacci(int seqNumber)
			throws IllegalArgumentException {
		if (seqNumber < 0)
			throw new IllegalArgumentException(
					"Negative number or zero is not allowed. Input seqNumber needs to be greater than zero. Please check your input and retry.");

		TreeMap<String, BigInteger> fibCache = new TreeMap<String, BigInteger>();
		for (int i = 0; i < seqNumber; i++) {
			fibCache.put(Integer.toString(i), BigInteger.valueOf(fib(i)));
			//System.out.println(fib(i));
		}
		return fibCache;
	}

	private int fib(int n) {
		int previousOne = 0;
		int preiviousTwo = 1;
		for (int i = 0; i < n; i++) {
			int keepPreviousOne = previousOne;
			previousOne = preiviousTwo;
			preiviousTwo = keepPreviousOne + preiviousTwo;
		}
		return previousOne;
	}

}
