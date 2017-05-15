package com.minhld.shareobjects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.minhld.devices.Device;

public class DeviceLocations {
	// causing too much trouble
	// QuadTree<Integer, String> quadTree = new QuadTree<Integer, String>();
	static HashMap<String, Point> deviceLocations = new HashMap<>();
	
	synchronized public static void updateLocation(String deviceKey, Point location) {
		deviceLocations.put(deviceKey, location);
	}
	
	/**
	 * find the nearby devices of a device which are within 
	 * the WiFi Direct maximum range.
	 * 
	 * @param device device which nearby devices are distanced to
	 * @return list of device keys
	 */
	synchronized public static String[] getNearbyDevices(Device device) {
		List<String> nearbyDevices = new ArrayList<String>();
		Point devLoc = null;
		for (String key : deviceLocations.keySet()) {
			// skip itself
			if (key.equals(device.name)) continue;
			
			devLoc = deviceLocations.get(key);
			if (isClosed(device.location, devLoc)) {
				nearbyDevices.add(key);
			}
		}
		return nearbyDevices.toArray(new String[] {});
	}
	
	private static boolean isClosed(Point p1, Point p2) {
		double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
		return distance < SimProperties.getWFDMaxRange();
	}
}
