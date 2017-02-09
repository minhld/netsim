package com.minhld.devices;

import java.awt.Point;

public class SimpleMovement extends Movement {

	public SimpleMovement(Point p11, Point p22) {
		super(p11, p22);
	}

	@Override
	public Point move() {
		return location;
	}
	
}
