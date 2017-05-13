package com.minhld.locations;

/******************************************************************************
 *  Compilation:  javac Interval.java
 *  Execution:    java Interval
 *
 *  Implementation of an interval.
 *
 ******************************************************************************/

public class Interval<Key extends Comparable<Key>> { 
	// min end-point
	private final Key min;
	// max end-point
    private final Key max;

    public Interval(Key min, Key max) {
        if (less(max, min)) throw new RuntimeException("Illegal argument");
        this.min = min;
        this.max = max;
    }

    /**
     * return min end-point
     * @return
     */
    public Key min() {
        return min;
    }

    /**
     * return max end-point
     * @return
     */
    public Key max() {
        return max;
    }

    /**
     * is x between min and max
     * 
     * @param x
     * @return
     */
    public boolean contains(Key x) {
        return !less(x, min) && !less(max, x);
    }

    /**
     * does this interval a intersect interval b?
     * @param b
     * @return
     */
    public boolean intersects(Interval<Key> b) {
        Interval<Key> a  = this;
        if (less(a.max, b.min)) return false;
        if (less(b.max, a.min)) return false;
        return true;
    }

    /**
     * does this interval a equal interval b?
     * @param b
     * @return
     */
    public boolean equals(Interval<Key> b) {
        Interval<Key> a  = this;
        return a.min.equals(b.min) && a.max.equals(b.max);
    }


    /**
     * comparison helper functions
     * @param x
     * @param y
     * @return
     */
    private boolean less(Key x, Key y) {
        return x.compareTo(y) < 0;
    }

    /**
     * return string representation
     */
    public String toString() {
        return "[" + min + ", " + max + "]";
    }

}
