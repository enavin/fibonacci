package com.sample.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.rest.entity.binding.GenericError;
import com.sample.rest.utility.DataTransformation;


/**
 * Base controller which has all the common REST service behaviors
 *
 * @author Naveen
 *
 */

public abstract class AbstractRESTController {
	
		private static final Logger logger = LoggerFactory.getLogger(AbstractRESTController.class);
		
		String exceptionJson = "{\"error\": {" 
								+ "\"-code\": \"500\","
								+ "\"message\": \"Processing Error\","
								+ "\"details\": \"Processing Error. Please contact support for further assistance\"}}\"";
		
		//TODO: enhance to support exceptions in json format using ExceptionHandler. 
		
		/**
		 * process illegal argument exception for xml based response.
		 * 
		 * @param illegalArgumentException
		 *           
		 * @return the generic error object
		 */
		@ExceptionHandler({IllegalArgumentException.class})
		ResponseEntity<GenericError> handleInvalidInput(IllegalArgumentException e) {
			return handleError(HttpStatus.BAD_REQUEST.value(), "Bad Request",e.getMessage(),e);
		}
		
		/**
		 * process exception for xml based response.
		 * 
		 * @param Exception
		 *            
		 * @return the generic error object
		 */
		@ExceptionHandler({Exception.class})
		ResponseEntity<GenericError> handleUnExpectedError(Exception e) {
			return handleError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Processing Error", "Processing Error. Please contact support for further assistance",e);
		}
		
		/**
		 * handle generic error.
		 * 
		 * @param Exception
		 *            
		 * @return the generic error object
		 */
		protected ResponseEntity<GenericError> handleError(int code, String message, String details, Exception e) {
			GenericError error = new GenericError();
			error.setCode(Integer.toString(code));
			error.setMessage(message);
			error.setDetails(details);
			logger.error("Error Code: " + code + " ,Message: " + message + " ,Details : " + details, e);
			return new ResponseEntity<GenericError>(error,HttpStatus.valueOf(code));
		}
		
		/**
		 * process illegal argument exception for json based response.
		 * 
		 * @param illegalArgumentException
		 *            
		 * @return the generic error object
		 */
		protected ResponseEntity<String> handleInvalidInputAsJson(IllegalArgumentException e)  {
			ResponseEntity<GenericError> response= handleInvalidInput(e);
			String errorJson ="";
			try {
				errorJson = DataTransformation.getDataAsString(response.getBody());
			} catch (Exception e1) {
				logger.error("Exception on handle invalid input as json conversion. ",e1);
				return new ResponseEntity<String>(exceptionJson,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			return new ResponseEntity<String>(errorJson,HttpStatus.BAD_REQUEST);
		}
		
		/**
		 * process exception for json based response.
		 * 
		 * @param illegalArgumentException
		 *            
		 * @return the generic error object
		 */
		protected ResponseEntity<String> handleUnExpectedErrorAsJson(Exception e)  {
			return new ResponseEntity<String>(exceptionJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
}
