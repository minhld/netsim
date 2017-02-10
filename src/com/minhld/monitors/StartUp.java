package com.minhld.monitors;

import java.awt.Point;
import java.util.HashMap;

import com.minhld.devices.Device;
import com.minhld.devices.MobileDevice;
import com.minhld.devices.SimpleMovement;

public class StartUp extends Thread {
	public void run() {
		int numOfDevs = 1;
		Point p11 = new Point(0, 0);
		Point p22 = new Point(1000, 1000);
		
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
	
	public static void main(String args[]) {
		new StartUp().start();
	}
}
