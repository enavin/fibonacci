package com.sample.rest.utility;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to convert object to json
 * 
 * @author Naveen
 *
 */
public class DataTransformation {
	
	private static final Logger logger = LoggerFactory.getLogger(DataTransformation.class);
	
	public static String getDataAsString(Object data) throws Exception{
		net.sf.json.JSONObject json;
		try {
			json = JSONObject.fromObject(data);
			logger.debug("JSON Output: " + json.toString());
			return json.toString();
		} catch (JSONException e) {
			logger.error("Failed to generate json string.",e);
			throw e;
		}
	}

}
