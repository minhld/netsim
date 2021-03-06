package com.minhld.devices;

import java.awt.Point;
import java.util.HashMap;

import com.minhld.movements.Movement;
import com.minhld.movements.MovementFactory;
import com.minhld.shareobjects.DeviceLocations;
import com.minhld.shareobjects.SimProperties;
import com.minhld.utils.SignalClient;
import com.minhld.wifidirect.NetworkUtils;

/**
 * represents a virtual device, it could be a mobile device, mobile edge device
 * or stationary server. 
 * 
 * @author minhld
 *
 */
public abstract class Device extends Thread {
	/**
	 * main device type
	 */
	public enum DeviceType {
		Mobile,
		Edge,
		Cloud
	}
	
	public String name;
	public DeviceType type;
	public Point location;
	
	public double ram;			// GB
	public double CPU;			// GHz
	public double battery;		// mAh
	
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
		// create random information (RAM, CPU and battery)
		setupInfo();
		
		// start randomly moving
		startMoving();
		
		// keep track of the nearby devices
		detectNearbyDevices();
		
		// start signal client to send locations to server
		new SignalClient2().start();
	}
	
	/**
	 * this function will terminate the device. this could happen 
	 * when device moves out of the management area or disconnect
	 * from socket connection. 
	 */
	public void term() {
		
	}
	
	/**
	 * setup basic information including ID, type and so on
	 */
	protected void setupInfo() {
		// this.name = UUID.randomUUID().toString();
		double[] specs = DeviceUtils.getRandomSpecs();
		this.ram = specs[0];
		this.CPU = specs[1];
		this.battery = specs[2];
	}
	
	/**
	 * updates the list of nearby devices
	 */
	protected void detectNearbyDevices() {
		// run on a different thread from the main
		new Thread() {
			@Override
			public void run() {
				// get the list of nearby devices
				String[] nearKeyList = DeviceLocations.getNearbyDevices(Device.this);
				// check if connections are available between this device
				// and the nearby ones
				NetworkUtils.checkConnections(nearKeyList);
				
				// wait for a while before restarting this loop
				try {
					sleep(SimProperties.getPeersDetectSpeed());
				} catch (Exception e) { }
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
					//System.out.println("[" + Device.this.name + "]" + Device.this.location.x + "," + Device.this.location.y);
					
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
	 * assign movement by ID. this will be matched with the 
	 * configuration file.
	 * 
	 * @param movementId
	 */
	public void setMovement(int movementId) {
		int simWidth = SimProperties.getIntProp("network-width");
		int simHeight = SimProperties.getIntProp("network-height");
		this.movement = MovementFactory.getMovement(movementId, simWidth, simHeight);
	}
	
	/**
	 * user needs to define the movement here
	 */
	public void setMovement(Movement movement) {
		this.movement = movement;
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
		public void nearbyDevicesUpdated(HashMap<String, Device> nearbyDevices);
		public void locationUpdated(Point location);
	}
	
	private class SignalClient2 extends SignalClient {
		@Override
		public String createResponse() {
			return Device.this.getDeviceInfo();
		}
	}
	
	public String getDeviceInfo() {
		// get list of the nearby devices
		String keySets = "";
		
		return "name=" + Device.this.getName() + ";" + 
				"x=" + Device.this.location.x + ";" + 
				"y=" + Device.this.location.y + ";" + 
				"nearbys=" + keySets + "";
	}
	
	
}
