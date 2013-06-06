package com.sample.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.rest.entity.binding.BadRequest;
import com.sample.rest.entity.binding.Fibonacci;
import com.sample.rest.entity.binding.FibonacciJSON;
import com.sample.rest.entity.binding.UnExpectedError;
import com.sample.rest.service.fibonacci.FibonacciService;
import com.sample.rest.utility.DataTransformation;



/**
 * This REST controller generates fibonacci series as output based on a given number. 
 * The result can be outputted in the form of XML or JSON. XML is the default.
 * 
 * @author Naveen
 */
@Controller
public class FibonacciController extends AbstractRESTController{

	private static final Logger logger = LoggerFactory.getLogger(FibonacciController.class);
	
	@Autowired
	FibonacciService fibonacciService;
	
	@RequestMapping(value = {"/fibonacci.xml","/fibonacci"}, method=RequestMethod.GET, produces= MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	ResponseEntity<Fibonacci> generateFibonacciAsXML(@RequestParam final int seqNumber) throws IllegalArgumentException, Exception{
		return generateFibonacci(seqNumber);
	}
	
	@RequestMapping(value = "/fibonacci.json", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResponseEntity<String> generateFibonacciAsJson(@RequestParam final int seqNumber) throws IllegalArgumentException, Exception{
		//revisit this functionality, on marshalling it is not including the root element "fibonacci" in json response due to @XmlRootElement annotation issue
		//Lets use the wrapper class for now
		ResponseEntity<Fibonacci> response = generateFibonacci(seqNumber);
		FibonacciJSON fibonacciJSON =  new FibonacciJSON(response.getBody());
		String result = DataTransformation.getDataAsString(fibonacciJSON);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	ResponseEntity<Fibonacci> generateFibonacci(@RequestParam final int seqNumber) throws IllegalArgumentException, Exception{
		if( seqNumber < 0)
			throw new IllegalArgumentException("Negative number is not allowed. Please check your input and retry.");
	
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
	
	//TODO: enhance to support exceptions in json format
	
	@ExceptionHandler({IllegalArgumentException.class})
	ResponseEntity<BadRequest> handleInvalidInput(Exception e) {
		BadRequest badRequest = new BadRequest();
		badRequest.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
		badRequest.setMessage("Bad Request"); //TODO: define all the text message in resource bundle
		badRequest.setDetails(e.getMessage());
		return new ResponseEntity<BadRequest>(badRequest,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({Exception.class})
	ResponseEntity<UnExpectedError> handleUnExpectedError(Exception e) {
		UnExpectedError error = new UnExpectedError();
		error.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		error.setMessage("Processing Error");
		error.setDetails("Processing Error. Please contact support for further assistance");
		return new ResponseEntity<UnExpectedError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "")
	ResponseEntity<String> unsupportedVerb(){
		return new ResponseEntity<String>("Sorry, the page/api you were looking for was not found. Please check the API document for available service",HttpStatus.NOT_FOUND);
	}
}
