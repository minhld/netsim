package com.minhld.movements;

public class MovementFactory {
	
	public static final int MOVE_ANY = 0;
	public static final int MOVE_SIMPLE = 1;
	public static final int MOVE_LINEAR = 2;
	public static final int MOVE_PARABOLIC = 3;
	public static final int MOVE_UNMOVED = 100;
	
	public static Movement getMovement(int movementId, int simWidth, int simHeight) {
		if (movementId == MOVE_ANY) {
			
		} else if (movementId == MOVE_SIMPLE) {
			return new SimpleMovement(simWidth, simHeight); 
		} else if (movementId == MOVE_LINEAR) {
			
		} else {
			
		}
		return null;
	}
}
