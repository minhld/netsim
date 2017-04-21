package com.minhld.movements;

import java.awt.Point;

/**
 * generate simple movements - the next movement will be randomly chosen
 * by increasing 1 in either x or y coordinator. 
 * 
 * @author minhld
 *
 */
public class SimpleMovement extends Movement {

	public SimpleMovement(int simWidth, int simHeight) {
		super(simWidth, simHeight);
	}
	
	public SimpleMovement(int simWidth, int simHeight, int period) {
		super(simWidth, simHeight, period);
	}

	@Override
	public Point move() {
		location = nextMove(location);
		return location;
	}
	
	private Point nextMove(Point currLocation) {
		int numOfDirects = 9;
		int i = (int) (Math.random() * numOfDirects);
		
		switch (i) {
			case 0 : {
				return currLocation;
			}
			case 1 : {
				return new Point(currLocation.x, currLocation.y + 1);
			}
			case 2 : {
				return new Point(currLocation.x, currLocation.y - 1);				
			}
			case 3 : {
				return new Point(currLocation.x + 1, currLocation.y);				
			}
			case 4 : {
				return new Point(currLocation.x + 1, currLocation.y + 1);				
			}
			case 5 : {
				return new Point(currLocation.x + 1, currLocation.y - 1);				
			}
			case 6 : {
				return new Point(currLocation.x - 1, currLocation.y);				
			}
			case 7 : {
				return new Point(currLocation.x - 1, currLocation.y + 1);				
			}
			case 8 : {
				return new Point(currLocation.x - 1, currLocation.y - 1);
			}
		}
		return currLocation;
	}
}
