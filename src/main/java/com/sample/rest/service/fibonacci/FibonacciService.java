/**
 * 
 */
package com.sample.rest.service.fibonacci;

import com.sample.rest.entity.binding.FibonacciBean;

/**
 * This interface retrieves the fibonacci series
 * 
 * @author Naveen
 */
public interface FibonacciService {
	
	
	/**
	 * Gets a fibonacci series
	 * 
	 * @param seqNumber
	 * 			the number of series that need to be generated
	 * @return Fibonacci series
	 */
	public FibonacciBean getFibonacci(int seqNumber) throws IllegalArgumentException;

}
