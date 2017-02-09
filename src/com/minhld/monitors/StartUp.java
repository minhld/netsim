package com.minhld.monitors;

import com.minhld.devices.MobileDevice;

public class StartUp extends Thread {
	public void run() {
		int numOfDevs = 100;
		MobileDevice dev;
		for (int i = 0; i < numOfDevs; i++) {
			dev = new MobileDevice();
			
			dev.start();
		}
	}
	
	public static void main(String args[]) {
		new StartUp().start();
	}
}
