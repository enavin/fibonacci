package com.sample.rest.service.fibonacci.impl;

import java.math.BigInteger;

import com.sample.rest.entity.binding.FibonacciBean;

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
	public FibonacciBean getFibonacci(int seqNumber)
			throws IllegalArgumentException {
		if (seqNumber < 0)
			throw new IllegalArgumentException(
					"Negative number or zero is not allowed. Input seqNumber needs to be greater than zero. Please check your input and retry.");
		FibonacciBean fibonacciBean = new FibonacciBean();
		for (int i = 0; i < seqNumber; i++) {
			fibonacciBean.addValue(Integer.toString(i),fibonacciGenerator(i).toString());
		}
		return fibonacciBean;
	}

	private BigInteger fibonacciGenerator(int n) {
		if(n ==0) return BigInteger.ZERO;
		BigInteger previousOne = BigInteger.ZERO;
		BigInteger preiviousTwo = BigInteger.ONE;
		for (int i = 0; i < n; i++) {
			BigInteger keepPreviousOne = previousOne;
			previousOne = preiviousTwo;
			preiviousTwo = keepPreviousOne.add(preiviousTwo);
		}
		return previousOne;
	}

}
