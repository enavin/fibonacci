package com.sample.rest.service.fibonacci.impl;

import com.sample.rest.entity.binding.FibonacciBean;
import com.sample.rest.service.fibonacci.FibonacciService;

/**
 * This implementation retrieves the fibonacci series via fibonacci iterative based module
 * TODO: enhance to detect the number of CPUs and switch to parallel processing fibonanci component for better performance and scalability
 *
 * @author Naveen
 */

public class FibonacciServiceImpl implements FibonacciService {

	/**
	 * Gets a fibonacci series
	 * 
	 * @param seqNumber
	 * 			the number of series that need to be generated
	 * @return Fibonacci series
	 */
	@Override
	public FibonacciBean getFibonacci(int seqNumber) throws IllegalArgumentException {
		FibonacciIterative fibIt = new FibonacciIterative();
		FibonacciBean fb = fibIt.getFibonacci(seqNumber);	
		return fb;
	}

}
