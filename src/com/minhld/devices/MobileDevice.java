package com.minhld.devices;

import java.awt.Point;
import java.util.HashMap;

public class MobileDevice extends Device {
	public MobileDevice() {
		
	}

	@Override
	public void connectToDevice() {

	}
	
	public class DeviceListener implements Device.DeviceListener {

		@Override
		public void discoveryCompleted() {
			
		}

		@Override
		public void connectionEstablished() {
			
		}

		@Override
		public void networkChanged() {
			
		}

		@Override
		public void locationUpdated(Point location) {
			
		}

		@Override
		public void nearbyDevicesUpdated(HashMap<String, Device> nearbyDevices) {
			
		}
		
	}
}
