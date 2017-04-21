package com.minhld.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class SimProperties {
	private static HashMap<String, String> innerProps = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	public static void loadProps(String file) {
		try {
			File f = new File("configs/" + file);
			Properties props = new Properties();
			props.load(new FileInputStream(f));
			
			Enumeration enuKeys = props.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = props.getProperty(key);
				innerProps.put(key, value);
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	public static String getProp(String propName) {
		return innerProps.get(propName);
	}
	
	/**
	 * Get property value in integer format. Make sure the property
	 * is in integer or else it throws exception
	 * 
	 * @param propName
	 * @return
	 */
	public static int getIntProp(String propName) {
		return Integer.parseInt(innerProps.get(propName));
	}
}
