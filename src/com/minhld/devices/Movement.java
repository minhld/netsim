package com.minhld.devices;

import java.awt.Point;

public abstract class Movement {
	public static final int DEFAULT_PERIOD = 500;
	protected Point firstLocation;
	protected Point location;
	protected int period;
	
	/**
	 * with default period 1 second
	 * @param p11: top-left location
	 * @param p22: bottom-right location
	 */
	public Movement(Point p11, Point p22) {
		this(p11, p22, DEFAULT_PERIOD);
	}
	
	/**
	 * 
	 * @param p11: top-left corner location
	 * @param p22: bottom-right corner location
	 * @param period
	 */
	public Movement(Point p11, Point p22, int period) {
		int x = p22.x - p11.x;
		int y = p22.y - p11.y;
		int newX = (int) (Math.random() * x);
		int newY = (int) (Math.random() * y);
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
