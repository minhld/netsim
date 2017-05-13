package com.minhld.locations;

public class Interval2D<Key extends Comparable<Key>> {
	// x-interval
	public final Interval<Key> intervalX;
	// y-interval
    public final Interval<Key> intervalY;
   
    public Interval2D(Interval<Key> intervalX, Interval<Key> intervalY) {
        this.intervalX = intervalX;
        this.intervalY = intervalY;
    }

    // does this 2D interval a intersect b?
    public boolean intersects(Interval2D<Key> b) {
        if (intervalX.intersects(b.intervalX)) return true;
        if (intervalY.intersects(b.intervalY)) return true;
        return false;
    }

    /**
     * does this 2D interval contain (x, y)?
     * @param x
     * @param y
     * @return
     */
    public boolean contains(Key x, Key y) {
        return intervalX.contains(x) && intervalY.contains(y);
    }

    /**
     * return string representation
     */
    public String toString() {
        return intervalX + " x " + intervalY;
    }

}
