package com.minhld.devices;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Device {
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
	public List<Device> deviceList = new ArrayList<>();
	
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
