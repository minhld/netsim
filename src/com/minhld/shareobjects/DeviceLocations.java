package com.minhld.shareobjects;

import java.awt.Point;
import java.util.HashMap;

import com.minhld.locations.QuadTree;

public class DeviceLocations {
	// causing too much trouble
	// QuadTree<Integer, String> quadTree = new QuadTree<Integer, String>();
	static HashMap<String, Point> deviceLocations = new HashMap<>();
	
	public static void updateLocation(String deviceKey, Point location) {
		deviceLocations.put(deviceKey, location);
	}
}
