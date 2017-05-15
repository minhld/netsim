package com.minhld.shareobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class SimProperties {
	private static HashMap<String, String> innerProps = new HashMap<>();
	
	private static int wfdMaxRange = 0;
	private static int detectPeersSpeed = 0;
	
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
	
	/**
     * get WiFi Direct max range in virtual unit
     * @return
     */
    public static int getWFDMaxRange() {
    	if (wfdMaxRange <= 0) {
    		int wfdVirtualMaxRange = SimProperties.getIntProp("wifi-direct-max-range");
    		int nwScale = SimProperties.getIntProp("network-scale");
    		wfdMaxRange = wfdVirtualMaxRange / nwScale;
    	}
    	return SimProperties.wfdMaxRange;
    }
    
    /**
     * get peers detection speed 
     * 
     * @return
     */
    public static int getPeersDetectSpeed() {
    	if (detectPeersSpeed <= 0) {
    		detectPeersSpeed = SimProperties.getIntProp("detect-peer-speed");
    	}
    	return SimProperties.detectPeersSpeed;
    }
}
