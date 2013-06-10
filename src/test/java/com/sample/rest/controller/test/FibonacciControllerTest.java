package com.sample.rest.controller.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.sample.rest.controller.test.common.RestHelper;

public class FibonacciControllerTest {


		RestHelper restHelper = new RestHelper();
		String serviceURL = "http://localhost:8080/sample/fibonacci";
		
		private Logger logger = LoggerFactory.getLogger(FibonacciControllerTest.class);
		String xmlTestData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><fibonacci><value index=\"0\">0</value><value index=\"1\">1</value><value index=\"2\">1</value><value index=\"3\">2</value><value index=\"4\">3</value></fibonacci>";
		String jsonTestData = "{\"value\":[{\"index\":\"0\",\"text\":\"0\"},{\"index\":\"1\",\"text\":\"1\"},{\"index\":\"2\",\"text\":\"1\"},{\"index\":\"3\",\"text\":\"2\"},{\"index\":\"4\",\"text\":\"3\"}]}";
		
		@Test
		public void setup(){
			// setup proxy or other perdefined data here...
		}
		
		/*
		 * This is commented out to make the maven automated test cases work
		 * This unit test requires the web service up and running on local.
		 * 
		 */
		//@Test  // this group method also you to comment and uncommtent easily for testing the REST web service
		public void testGroup() {
			testGetFibonacciXML();
			testGetFibonacciXMLInvalidInput();
			testGetFibonacciJSON();
		}
		
		@Test
		public void testGetFibonacciXML() {

			HttpHeaders requestHeaders = new HttpHeaders();
			

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			parameters.add("seqNumber", "5");
			
			ResponseEntity<String> result;
			try {
				
				logger.debug("URL :" + serviceURL);
				
				result = restHelper.get(serviceURL + ".xml", requestHeaders, parameters);
				assertNotNull(result);
				
				assertEquals(HttpStatus.OK.value(),result.getStatusCode().value());
				
				assertNotNull(result.hasBody());
				
				String actualXML = result.getBody();
				
				assertEquals(xmlTestData.trim(),actualXML.trim());
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				fail();
			}
		}	
		
		@Test
		public void testGetFibonacciXMLInvalidInput() {

			HttpHeaders requestHeaders = new HttpHeaders();

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			parameters.add("seqNumber", "-1");
			
			ResponseEntity<String> result;
			try {
				
				logger.debug("URL :" + serviceURL);
				
				result = restHelper.get(serviceURL + ".xml", requestHeaders, parameters);
				assertNotNull(result);
				
				assertEquals(HttpStatus.BAD_REQUEST.value(),result.getStatusCode().value());
				
			} catch (Exception e) {
				e.printStackTrace();
				assertNotNull(e.getMessage().indexOf(400) > 0);
				
			}
		}	
		
		@Test
		public void testGetFibonacciJSON() {
			HttpHeaders requestHeaders = new HttpHeaders();

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			parameters.add("seqNumber", "5");
			
			ResponseEntity<String> result;
			try {
				
				logger.debug("URL :" + serviceURL);
				
				 result = restHelper.get(serviceURL + ".json", requestHeaders, parameters);
				assertNotNull(result);
				
				assertEquals(HttpStatus.OK.value(),result.getStatusCode().value());
				
				assertNotNull(result.hasBody());
				
				JSONObject expectedObject = JSONObject.fromObject( jsonTestData );  
				
				JSONObject actualObject = JSONObject.fromObject( result.getBody() );  
				
				assertEquals(expectedObject,actualObject);
				
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}	
		
	
}
