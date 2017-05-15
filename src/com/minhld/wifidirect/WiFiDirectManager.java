package com.minhld.wifidirect;

import com.minhld.devices.Device;

public class WiFiDirectManager extends NetworkManager {
	
	private NetworkListener listener;
	
	public boolean isConnected = false;
	public boolean groupOwner = false;
	
	public WiFiDirectManager(Device device) {
		super(device);
	}
	
	public void communicate(String[] nearbyList) {
		
	}
	
	public void setNetworkListener(NetworkListener listener) {
		this.listener = listener;
	}
	
	public interface NetworkListener {
		
	}
}
