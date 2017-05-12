package com.minhld.utils;

import java.util.HashMap;
import java.util.Set;

import com.minhld.devices.Device;

public class DeviceList {
	private static HashMap<String, Device> deviceList = new HashMap<>();
	
	public static void add(String key, Device dev) {
		DeviceList.deviceList.put(key, dev);
	}
	
	public static Device get(String key) {
		return DeviceList.deviceList.get(key);
	}
	
	public static Set<String> getKeySet() {
		return DeviceList.deviceList.keySet();
	}
	
	public static int length() {
		return DeviceList.deviceList.size();
	}
}
