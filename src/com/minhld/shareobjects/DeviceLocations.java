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
	
	public static void updateLocation(String deviceKey, Point location) {
		deviceLocations.put(deviceKey, location);
	}
	
	/**
	 * find the nearby devices of a device which are within 
	 * the WiFi Direct maximum range.
	 * 
	 * @param device device which nearby devices are distanced to
	 * @return list of device keys
	 */
	public static String[] getNearbyDevices(Device device) {
		List<String> nearbyDevices = new ArrayList<String>();
		Point devLoc = null;
		double distance = 0;
		for (String key : deviceLocations.keySet()) {
			devLoc = deviceLocations.get(key);
			distance = estimateDistance(device.location, devLoc);
			if (distance < SimProperties.getWFDMaxRange()) {
				nearbyDevices.add(key);
			}
		}
		return nearbyDevices.toArray(new String[] {});
	}
	
	private static double estimateDistance(Point p1, Point p2) {
		return 0;
	}
}
