package com.minhld.devices;

import java.awt.Point;
import java.util.HashMap;

public class MobileDevice extends Device {
	public MobileDevice() {
		
	}

	@Override
	public void connectToDevice() {
		// TODO Auto-generated method stub
		
	}
	
	public class DeviceListener implements Device.DeviceListener {

		@Override
		public void discoveryCompleted() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connectionEstablished() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void networkChanged() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void locationUpdated(Point location) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deviceListUpdated(HashMap<String, Device> nearbyDevices) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
