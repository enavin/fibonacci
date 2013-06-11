package com.sample.rest.service.fibonacci.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.sample.rest.entity.binding.FibonacciBean;
import com.sample.rest.entity.binding.Value;
import com.sample.rest.service.fibonacci.FibonacciService;

/**
 * This class generates fibonacci using iterative model and cache the content.
 * 
 * @author Naveen
 * 
 */
@Singleton
public class FibonacciServiceIterativeImpl implements FibonacciService{
	
	private static List<Value> values = new ArrayList<Value>();
	
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
		int listSize = values.size();
		int endSeq =0;
		if(!values.isEmpty()){
			endSeq = (listSize >= seqNumber) ? seqNumber : listSize;
			fibonacciBean.getValue().addAll(values.subList(0, endSeq));
		}

		for (int i = endSeq; i < seqNumber; i++) {
			values.add( new Value(Integer.toString(i),fibonacciGenerator(i).toString()));
		}
		fibonacciBean.getValue().addAll(values.subList(endSeq, seqNumber));
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
