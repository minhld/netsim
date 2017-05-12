package com.minhld.monitors;

import java.awt.Point;
import java.util.HashMap;


import com.minhld.devices.Device;
import com.minhld.devices.MobileDevice;
import com.minhld.utils.DeviceList;
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
		
		int movementId = SimProperties.getIntProp("movement-id");
		
		MobileDevice dev;
		String deviceKey;
		for (int i = 0; i < numOfDevs; i++) {
			deviceKey = Integer.toString(i);
			dev = new MobileDevice();
			dev.name = deviceKey;
			dev.setMovement(movementId);
			dev.setDeviceListener(new SubDeviceListener(deviceKey));
			DeviceList.add(deviceKey, dev);
			dev.start();
		}
	}
	
	/**
	 * this class implements the Device Listener interface
	 * 
	 * @author minhld
	 *
	 */
	class SubDeviceListener implements Device.DeviceListener {
		String deviceKey;
		
		public SubDeviceListener(String key) {
			this.deviceKey = key;
		}
		
		@Override
		public void networkChanged() {
			
		}
		
		@Override
		public void locationUpdated(Point location) {
			Device device = DeviceList.get(deviceKey);
			device.location = location;
			DeviceList.add(deviceKey, device);
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
	}
	
	public interface DeviceStartListener {
		public void deviceLocationUpdated();
	}
}
