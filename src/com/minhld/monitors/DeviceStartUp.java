package com.minhld.monitors;

import java.awt.Point;
import java.util.HashMap;


import com.minhld.devices.Device;
import com.minhld.devices.MobileDevice;
import com.minhld.devices.SimpleMovement;
import com.minhld.utils.SignalServer;
import com.minhld.utils.SimProperties;

public class DeviceStartUp extends Thread {
	private DeviceStartListener listener;
	
	public void setDeviceStartListener(DeviceStartListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		// get number of devices
		int numOfDevs = SimProperties.getIntProp("num-of-nodes");
		
		// points to define top-left and bottom-right corners 
		Point p11 = new Point(0, 0);
		int width = SimProperties.getIntProp("network-width");
		int height = SimProperties.getIntProp("network-height");
		Point p22 = new Point(width, height);
		
		// initiate signal server
		SignalServer signalServer = new SignalServer();
		signalServer.setDeviceNumber(numOfDevs);
		signalServer.setSignalServerListener(new SignalServer.SignalServerListener() {
			@Override
			public void allAcksReceived() {
				if (DeviceStartUp.this.listener != null) {
					DeviceStartUp.this.listener.deviceLocationUpdated();
				}
			}

			@Override
			public void updateDeviceInfo() {
				
			}
		});
		signalServer.start();
		
		MobileDevice dev;
		for (int i = 0; i < numOfDevs; i++) {
			dev = new MobileDevice();
			dev.setMovement(new SimpleMovement(p11, p22));
			dev.setDeviceListener(new Device.DeviceListener() {
				
				@Override
				public void networkChanged() {
					
				}
				
				@Override
				public void locationUpdated(Point location) {
					
				}
				
				@Override
				public void discoveryCompleted() {
					
				}
				
				@Override
				public void nearbyDevicesUpdated(HashMap<String, Device> nearbyDevices) {
					
				}
				
				@Override
				public void connectionEstablished() {
					
				}
			});
			dev.start();
		}
	}
	
	public interface DeviceStartListener {
		public void deviceLocationUpdated();
	}
}
