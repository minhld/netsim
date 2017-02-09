package com.minhld.devices;

import java.awt.Point;

public abstract class Movement {
	protected Point location;
	protected int period;
	
	/**
	 * the period may be randomly changed
	 * @param p11
	 * @param p22
	 */
	public Movement(Point p11, Point p22) {
		
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
		this.period = period;
	}
	
	/**
	 * move to the next possible position
	 * @return
	 */
	public abstract Point move();
	
}
