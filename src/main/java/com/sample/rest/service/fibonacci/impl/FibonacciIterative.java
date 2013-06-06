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

	private int fib(int n) {
            int previousOne=0;
            int preiviousTwo=1;
            for(int i=0; i<n; i++) {
                int keepPreviousOne = previousOne;
                previousOne = preiviousTwo;
                preiviousTwo = keepPreviousOne + preiviousTwo;
            }
            return previousOne;
	}

	public TreeMap<String,BigInteger> getFibonacci(int fibNumber) {
		TreeMap<String,BigInteger> fibCache = new TreeMap<String,BigInteger>();
		for (int i = 0; i < fibNumber; i++){
			fibCache.put(Integer.toString(i), BigInteger.valueOf(fib(i)));
			System.out.print(fib(i) + ", ");
		}
		return fibCache;
	}

}
