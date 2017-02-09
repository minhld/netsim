package com.minhld.devices;

import java.awt.Point;

/**
 * generate simple movements - the next movement will be randomly chosen
 * by increasing 1 in either x or y coordinator. 
 * 
 * @author minhld
 *
 */
public class SimpleMovement extends Movement {

	public SimpleMovement(Point p11, Point p22) {
		super(p11, p22);
	}
	
	public SimpleMovement(Point p11, Point p22, int period) {
		super(p11, p22, period);
	}

	@Override
	public Point move() {
		return location;
	}
	
}
