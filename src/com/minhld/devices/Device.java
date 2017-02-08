package com.minhld.devices;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a virtual device, it could be a mobile device, mobile edge device
 * or stationary server. 
 * 
 * @author minhld
 *
 */
public abstract class Device extends Thread {
	public enum DeviceType {
		Mobile,
		MobileEdge,
		StationaryServer
	}
	
	public String name;
	public DeviceType type;
	public Point location;
	/**
	 * list of nearby device
	 */
	public List<Device> nearbyDevices = new ArrayList<>();
	
	public DeviceListener listener;
	
	public void setDeviceListener(DeviceListener listener) {
		this.listener = listener;
	}
	
	/**
	 * the main entry points
	 */
	public void run() {
		
	}
	
	/**
	 * updates the list of nearby devices
	 */
	protected void detectNearbyDevices() {
		new Thread(){
			@Override
			public void run() {
				
			}
		}.start();
	}
	
	/**
	 * broadcast an advertisement message to the nearby devices
	 */
	protected void discovery() {
		
	}
	
	/**
	 * connects to a nearby device
	 */
	protected void connectToDevice() {
		
	}
	
	/** specifications of the device
	 * 
	 * @author minhle
	 */
	public class Specs {
		public float cpu;
		public float cpuPerc;
		public int ram;
		public float ramPerc;
		public int batteryCap;
		public float batteryPerc;
		public float RL;
	}
	
	/**
	 * provides the listener to the device object
	 * @author minhle
	 *
	 */
	public interface DeviceListener {
		public void discoveryCompleted();
		public void connectionEstablished();
		public void networkChanged();
		public void deviceListUpdated();
	}
}
