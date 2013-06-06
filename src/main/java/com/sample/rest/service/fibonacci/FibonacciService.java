/**
 * 
 */
package com.sample.rest.service.fibonacci;

import com.sample.rest.entity.binding.Fibonacci;

/**
 * This interface retrieves the fibonacci series
 * 
 * @author Naveen
 */
public interface FibonacciService {
	
	/**
	 * @param seqNumber
	 * @return Fibonacci sequence & series
	 */
	public Fibonacci getFibonacci(int seqNumber);

}
