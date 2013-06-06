package com.sample.rest.service.fibonacci.impl;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.TreeMap;

import com.sample.rest.entity.binding.Fibonacci;
import com.sample.rest.service.fibonacci.FibonacciService;

/**
 * This implementation retrieves the fibonacci series via fibonacci iterative based module
 * TODO: enhance to detect the number of CPUs and switch to parallel processing fibonanci component for better performance and scalability
 *
 * @author Naveen
 */

public class FibonacciServiceImpl implements FibonacciService {

	@Override
	public Fibonacci getFibonacci(int seqNumber) {
		FibonacciIterative fibIt = new FibonacciIterative();
		TreeMap<String,BigInteger> fibResult = fibIt.getFibonacci(seqNumber);	
		Fibonacci fb = convertToFibonacciBean(fibResult);
		return fb;
	}

	private Fibonacci convertToFibonacciBean(
			TreeMap<String, BigInteger> fibResult) {
		Fibonacci fb = new Fibonacci();
		for (Iterator<String> seq = fibResult.keySet().iterator(); seq.hasNext();){
			String key = seq.next();
			fb.addValue(key, String.valueOf(fibResult.get(key)));
		}
		return fb;
	}

}
