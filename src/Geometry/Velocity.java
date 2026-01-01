// 216700930 Amit Solomon

package Geometry;

/**
 * The Geometry.Velocity class represents the change in position on the x and y axes.
 * It includes methods to apply velocity to a point.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a Geometry.Velocity object with the specified change in position.
     *
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Geometry.Velocity from a given angle and speed.
     *
     * @param angle the angle of movement in degrees
     * @param speed the speed of movement
     * @return a Geometry.Velocity representing the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianValue = Math.toRadians(angle); // convert angle to radians
        double dx = speed * Math.cos(radianValue);
        double dy = speed * Math.sin(radianValue);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point to which the velocity is applied
     * @return a new point with updated position based on the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return the dx value of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value of the velocity
     */
    public double getDy() {
        return this.dy;
    }
}