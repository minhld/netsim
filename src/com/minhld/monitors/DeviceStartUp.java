package com.minhld.monitors;

import java.awt.Point;
import java.util.HashMap;


import com.minhld.devices.Device;
import com.minhld.devices.MobileDevice;
import com.minhld.devices.SimpleMovement;
import com.minhld.utils.SignalServer;

public class DeviceStartUp extends Thread {
	private DeviceStartListener listener;
	
	public void setDeviceStartListener(DeviceStartListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		int numOfDevs = 10;
		// points to define top-left and bottom-right corners 
		Point p11 = new Point(0, 0);
		Point p22 = new Point(10000, 10000);
		
		// initiate signal server
		SignalServer signalServer = new SignalServer();
		signalServer.setDeviceNumber(numOfDevs);
		signalServer.setSignalServerListener(new SignalServer.SignalServerListener() {
			@Override
			public void allAcksReceived() {
				DeviceStartUp.this.listener.deviceLocationUpdated();
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
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void locationUpdated(Point location) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void discoveryCompleted() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void deviceListUpdated(HashMap<String, Device> nearbyDevices) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void connectionEstablished() {
					// TODO Auto-generated method stub
					
				}
			});
			dev.start();
		}
	}
	
	public interface DeviceStartListener {
		public void deviceLocationUpdated();
	}
}
