package com.sample.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.rest.entity.binding.Fibonacci;
import com.sample.rest.entity.binding.FibonacciJSON;
import com.sample.rest.service.fibonacci.FibonacciService;
import com.sample.rest.utility.DataTransformation;



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
	@RequestMapping(value = {"/fibonacci.xml","/fibonacci"}, method=RequestMethod.GET, produces= MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	ResponseEntity<Fibonacci> generateFibonacciAsXML(@RequestParam final int seqNumber) throws IllegalArgumentException, Exception{
		return generateFibonacci(seqNumber);
	}
	
	/**
	 * Gets a fibonacci series in json format.
	 * Verb: GET
	 * 
	 * @param seqNumber
	 *            the number of series that need to be generated
	 * @return the fibonancci series
	 */
	@RequestMapping(value = "/fibonacci.json", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<String> generateFibonacciAsJson(@RequestParam final int seqNumber){
		//revisit this functionality, on marshalling it is not including the root element "fibonacci" in json response due to @XmlRootElement annotation issue
		//Lets use the wrapper class for now
		try {
			ResponseEntity<Fibonacci> response = generateFibonacci(seqNumber);
			FibonacciJSON fibonacciJSON =  new FibonacciJSON(response.getBody());
			String result = DataTransformation.getDataAsString(fibonacciJSON);
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}catch(IllegalArgumentException e){
			return handleInvalidInputAsJson(e);
		}catch (Exception e){
			return handleUnExpectedErrorAsJson(e);
		}
	}
	
	/**
	 * Gets a fibonacci series as an object.
	 * 
	 * @param seqNumber
	 *            the number of series that need to be generated
	 *            
	 * @return the fibonancci series object
	 */
	ResponseEntity<Fibonacci> generateFibonacci(final int seqNumber) throws IllegalArgumentException, Exception{
		if( seqNumber < 0)
			throw new IllegalArgumentException("Negative number or zero is not allowed. Input seqNumber needs to be greater than zero. Please check your input and retry.");
	
		try {
			// call the service and get the fibonacci sequence & series
			Fibonacci fibResult = fibonacciService.getFibonacci(seqNumber);
			logger.debug("Fibonace for value " + seqNumber + " is - " + fibResult);
			return new ResponseEntity<Fibonacci>(fibResult,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to process the request. Exception - " ,e);
		}
		
		return new ResponseEntity<Fibonacci>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
