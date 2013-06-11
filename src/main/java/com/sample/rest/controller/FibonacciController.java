package com.sample.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.rest.entity.binding.FibonacciBean;
import com.sample.rest.entity.binding.FibonacciJSON;
import com.sample.rest.service.fibonacci.FibonacciService;



/**
 * This REST controller generates fibonacci series based on a given number. 
 * The result can be outputted in the form of XML or JSON. XML is the default.
 * 
 * The web service accepts a number, n, as input and returns the first n Fibonacci numbers, starting from 0. I.e. given n = 5, 
 * appropriate output would represent the sequence "0 1 1 2 3".
 * 
 * @author Naveen
 */
@Controller
public class FibonacciController extends AbstractRESTController{

	private static final Logger logger = LoggerFactory.getLogger(FibonacciController.class);
	
	@Autowired
	FibonacciService fibonacciService;
	
	
	/**
	 * Gets a fibonacci series in xml format.
	 * Verb: GET
	 * 
	 * @param seqNumber
	 *            the number of series that need to be generated
	 * @return the fibonancci series
	 */
	@RequestMapping(value = {"/fibonacci"}, method=RequestMethod.GET, headers="Accept=application/json, application/xml")
	@ResponseBody
	ResponseEntity<FibonacciBean> generateFibonacciAsXML(@RequestParam final int seqNumber) throws IllegalArgumentException, Exception{
		return generateFibonacci(seqNumber);
	}

	/**
	 * Gets a fibonacci series as an object.
	 * 
	 * @param seqNumber
	 *            the number of series that need to be generated
	 *            
	 * @return the fibonancci series object
	 */
	ResponseEntity<FibonacciBean> generateFibonacci(final int seqNumber) throws IllegalArgumentException, Exception{
		if( seqNumber < 0)
			throw new IllegalArgumentException("Negative number or zero is not allowed. Input seqNumber needs to be greater than zero. Please check your input and retry.");
	
		try {
			// call the service and get the fibonacci series
			FibonacciBean fibonacciBean = fibonacciService.getFibonacci(seqNumber);
			logger.debug("Fibonace for value " + seqNumber + " is - " + fibonacciBean);
			return new ResponseEntity<FibonacciBean>(fibonacciBean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to process the request. Exception - " ,e);
		}
		
		return new ResponseEntity<FibonacciBean>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
