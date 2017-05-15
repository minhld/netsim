package com.minhld.wifidirect;

import com.minhld.devices.Device;

public abstract class NetworkManager {
	protected Device device;
	
	public NetworkManager(Device parent) {
		this.device = parent;
	}
}
