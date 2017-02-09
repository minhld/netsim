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
		// generate a new location based on the current one
		location = genRandLocation(location);
		return location;
	}
	
	private Point genRandLocation(Point currentLocation) {
		int rand = (int) Math.floor(Math.random() * 9);
		switch (rand) {
			case 0 : {
				// no moving
				return currentLocation;
			}
			case 1 : {
				return new Point(currentLocation.x, currentLocation.y + 1);
			}
			case 2 : {
				return new Point(currentLocation.x + 1, currentLocation.y + 1);
			}
			case 3 : {
				return new Point(currentLocation.x - 1, currentLocation.y + 1);
			}
			case 4 : {
				return new Point(currentLocation.x, currentLocation.y - 1);
			}
			case 5 : {
				return new Point(currentLocation.x + 1, currentLocation.y - 1);
			}
			case 6 : {
				return new Point(currentLocation.x - 1, currentLocation.y - 1);
			}
			case 7 : {
				return new Point(currentLocation.x + 1, currentLocation.y);
			}
			case 8 : {
				return new Point(currentLocation.x - 1, currentLocation.y);
			}
		}
		return currentLocation;
	}
}
