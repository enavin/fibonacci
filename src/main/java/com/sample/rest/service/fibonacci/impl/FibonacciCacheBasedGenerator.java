package com.sample.rest.service.fibonacci.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class establishes a memoization "cache", which stores previously computed fibonacci numbers. 
 * This is slightly faster than other solution. The iterative solution is faster, but still repeats a lot of calculations when computing successive fibonacci numbers.
 * TODO: check other algorithm to speed up the generation and for better scalability
 * 
 * @author Naveen
 *
 */
public class FibonacciCacheBasedGenerator {

	private ArrayList<BigInteger> fibGenCache = new ArrayList<BigInteger>();

	private BigInteger fib(int n) {
		if (n >= fibGenCache.size()) {
			fibGenCache.add(n, fib(n - 1).add(fib(n - 2)));
		}
		return fibGenCache.get(n);
	}

	public TreeMap<String,BigInteger> getFibonacci(int fibNumber) {
		fibGenCache.add(BigInteger.ZERO);
		fibGenCache.add(BigInteger.ONE);
		
		TreeMap<String,BigInteger> fibCache = new TreeMap<String,BigInteger>();
		for (int i = 0; i < fibNumber; i++){
			fibCache.put(Integer.toString(i),fib(i));
			//System.out.print(fib(i) + ", ");
		}
		return fibCache;
	}

}
