package com.minhld.devices;

import java.awt.Point;
import java.util.HashMap;
import java.util.UUID;

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
	
	protected String name;
	protected DeviceType type;
	protected Point location;
	/**
	 * list of nearby device
	 */
	public HashMap<String, Device> nearbyDevices = new HashMap<String, Device>();
	/**
	 * main listener
	 */
	protected DeviceListener listener;
	public void setDeviceListener(DeviceListener listener) {
		this.listener = listener;
	}
	/**
	 * movement period
	 */
	protected Movement movement;
	
	/**
	 * the main entry points
	 */
	public void run() {
		// create random information
		setupInfo();
		
		// start randomly moving
		startMoving();
	}
	
	/**
	 * setup basic information including ID, type and so on
	 */
	protected void setupInfo() {
		this.name = UUID.randomUUID().toString();
	}
	
	/**
	 * updates the list of nearby devices
	 */
	protected void detectNearbyDevices() {
		// run on a different thread from the main
		new Thread() {
			@Override
			public void run() {
				
			}
		}.start();
	}
	
	/**
	 * broadcast an advertisement message to the nearby devices
	 */
	protected void discover() {
		new Thread() {
			@Override
			public void run() {
				
			}
		}.start();
	}
	
	
	/**
	 * starts moving in the virtual environment
	 */
	protected void startMoving() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					// update location on this device
					Device.this.location = Device.this.movement.move();
					System.out.println("[" + Device.this.name + "]" + Device.this.location.x + "," + Device.this.location.y);
					
					// send location change event to the monitor
					listener.locationUpdated(Device.this.location);
					
					// sleep for a moment
					try {
						sleep(Device.this.movement.period);
					} catch (Exception e) { }
				}
			}
		}.start();
	}

	/**
	 * connects to a nearby device
	 */
	public abstract void connectToDevice();

	/**
	 * user needs to define the movement here
	 */
	public void setMovement(Movement movement) {
		this.movement = movement;
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
		public void deviceListUpdated(HashMap<String, Device> nearbyDevices);
		public void locationUpdated(Point location);
	}
}
