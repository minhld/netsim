package com.minhld.devices;

public class DeviceUtils {
	
	/**
	 * get random specification for RAM, CPU and battery
	 * 
	 * @return
	 */
	public static double[] getRandomSpecs() {
		double[] specs = new double[3];
		// RAM could be any number from 1 to 4
		specs[0] = (double)Math.round(Math.random() * 3) + 1;
		
		// CPU could be any number from 1 - 3
		specs[1] = ((double)Math.round(Math.random() * 20) + 10) / 10;
		
		// battery could be any number from 1000 - 4000
		specs[2] = ((double)Math.round(Math.random() * 6) + 2) * 500;
		
		// System.out.println("specs: " + specs[0] + "," + specs[1] + "," + specs[2]);
		
		return specs;
	}
	
	
}
