package com.minhld.movements;

import java.awt.Point;

public abstract class Movement {
	public static final int DEFAULT_PERIOD = 500;
	protected Point firstLocation;
	protected Point location;
	public int period;
	
	/**
	 * with default period 
	 * 
	 * @param simWidth: width of the entire simulation
	 * @param simHeight: height of the simulation
	 */
	public Movement(int simWidth, int simHeight) {
		this(simWidth, simHeight, DEFAULT_PERIOD);
	}
	
	/**
	 * Constructor with period
	 * 
	 * @param simWidth: width of the entire simulation
	 * @param simHeight: height of the simulation
	 * @param period
	 */
	public Movement(int simWidth, int simHeight, int period) {
		int newX = (int) (Math.random() * simWidth);
		int newY = (int) (Math.random() * simHeight);
		this.location = new Point(newX, newY);
		this.firstLocation = this.location;
		this.period = period;
	}
	
	/**
	 * move to the next possible position
	 * @return
	 */
	public abstract Point move();
	
}
