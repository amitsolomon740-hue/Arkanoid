// 216700930 Amit Solomon

package Geometry;

/**
 * This class represents a point by (x,y).
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a Geometry.Point with the specified x and y values.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance from this point to another point.
     *
     * @param other the other point
     * @return the distance  of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    }

    /**
     * checks if it is equal to another point.
     * Two points are considered equal if they have the same coordinates.
     *
     * @param other the point to compare with this point
     * @return return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < Constants.COMPARISON_THRESHOLD)
                && Math.abs(this.y - other.y) < Constants.COMPARISON_THRESHOLD;
    }

    /**
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x value of the Geometry.Ball.
     *
     * @param x the new x value of the Geometry.Ball
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y value of the Geometry.Ball.
     *
     * @param y the new y value of the Geometry.Ball
     */
    public void setY(double y) {
        this.y = y;
    }

}
