package com.minhld.wifidirect;

import com.minhld.devices.Device;

public class WiFiDirectManager extends NetworkManager {
	
	public boolean isConnected = false;
	public boolean groupOwner = false;
	
	private NetworkListener listener;

	public void setNetworkListener(NetworkListener listener) {
		this.listener = listener;
	}

	public WiFiDirectManager(Device device) {
		super(device);
	}
	
	public void communicate(String[] nearbyList) {
		
	}
	
	/**
	 * return events occurred by the Network Manager
	 * 
	 * @author minhle
	 *
	 */
	public interface NetworkListener {
		public void connected(String devKeys[]);
		public void disconnected(String devKeys[]);
		public void isGroupOwner(boolean isGroupOwner);
	}
}
