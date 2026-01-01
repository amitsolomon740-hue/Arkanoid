// 216700930 Amit Solomon

package Geometry;

/**
 * This class represents a line defined by two points.
 * It provides methods to calculate the length of the line segment, find its midpoint,
 * and determine if it intersects with another line.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructs a Geometry.Line with the start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a Geometry.Line with the specified coordinates for the start and end points.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    //a, b, c They are methods that calculate values that are used to calculate the intersection between the lines,
    // the calculation is done in the way described in the video: https://youtu.be/bvlIYX9cgls?si=S_DXYv63leq7av09

    /**
     * Calculates the value 'a' used in intersection calculations with another line.
     *
     * @param other the other line
     * @return the calculated value 'a'
     */
    public double a(Line other) {
        return (other.end.getX() - other.start.getX()) * (other.start.getY() - this.start.getY())
                - (other.end.getY() - other.start.getY()) * (other.start.getX() - this.start.getX());
    }

    /**
     * Calculates the value 'b' used in intersection calculations with another line.
     *
     * @param other the other line
     * @return the calculated value 'b'
     */
    public double b(Line other) {
        return (other.end.getX() - other.start.getX()) * (this.end.getY() - this.start.getY())
                - (other.end.getY() - other.start.getY()) * (this.end.getX() - this.start.getX());
    }

    /**
     * Calculates the value 'c' used in intersection calculations with another line.
     *
     * @param other the other line
     * @return the calculated value 'c'
     */
    public double c(Line other) {
        return (this.end.getX() - this.start.getX()) * (other.start.getY() - this.start.getY())
                - (this.end.getY() - this.start.getY()) * (other.start.getX() - this.start.getX());
    }

    /**
     * Determines if this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double a = a(other), b = b(other), c = c(other);
        return a / b < 1 + Constants.COMPARISON_THRESHOLD && a / b > 0 - Constants.COMPARISON_THRESHOLD
                && c / b > 0 - Constants.COMPARISON_THRESHOLD && c / b < 1 + Constants.COMPARISON_THRESHOLD
                //Checking if the lines converge.
                || a == 0 && b == 0
                // Checking if one line is a point, then the intersection is if this point is on the other line.
                && !other.start().equals(other.end()) && !this.start.equals(this.end)
                || other.start().equals(other.end()) && isPointOnLine(this, other.start());
    }

    /**
     * Determines if this line intersects with two other lines.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if this 2 lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null if the lines converge (with infinite cutting point) and otherwise.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        // Checking if one line is a point, then the intersection is if this point is on the other line.
        if (other.start().equals(other.end()) && isPointOnLine(this, other.start())) {
            return other.start();
        }
        if (this.start.equals(this.end) && isPointOnLine(other, this.start)) {
            return this.start();
        }

        double a = a(other), b = b(other);
        //Checking if the lines converge
        if (a == 0 && b == 0) {
            if (this.start.equals(other.start()) && !isPointOnLine(other, this.end)) {
                return this.start();
            } else if (this.end.equals(other.start()) && !isPointOnLine(other, this.start)) {
                return this.start();
            } else if (other.start().equals(this.start()) && !isPointOnLine(this, other.end())) {
                return other.start();
            } else if (other.end.equals(this.start()) && !isPointOnLine(this, other.start())) {
                return other.end();
            }
            //with infinite cutting point
            return null;
        }

        // Calculating the intersection point if it exists.
        if (isIntersecting(other)) {
            double x = this.start.getX() + a / b * (this.end.getX() - this.start.getX());
            double y = this.start.getY() + a / b * (this.end.getY() - this.start.getY());
            return new Point(x, y);
        }
        return null;
    }

    /**
     * Determines if this line is equal to another line.
     * Two lines are considered equal if they have the same start and end points,
     * regardless of the order.
     *
     * @param other the other line to compare with this line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.end.equals(other.start) && this.start.equals(other.end);
    }

    /**
     * @param l the line
     * @param p the point to check
     * @return true if the point is on the line, false otherwise
     */
    public static boolean isPointOnLine(Line l, Point p) {
        // Calculate the distance between the point and each edge point of the line
        double dFromStart = l.start().distance(p);
        double dFromEnd = l.end().distance(p);
        double lineLen = l.length();
        return Math.abs(dFromStart + dFromEnd - lineLen) < Constants.COMPARISON_THRESHOLD;
    }
    /**
     * If this line does not intersect with the rectangle, returns null.
     * Otherwise, returns the closest intersection point to the start of the line.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionsLst = rect.intersectionPoints(this);
        //if no intersection
        if (intersectionsLst.isEmpty()) {
            return null;
        }

        //checks the closest intersection point to the start of the line.
        double minD = Double.MAX_VALUE;
        Point closestIntersection = null;
        for (Point p : intersectionsLst) {
            double d = p.distance(this.start);
            if (d - minD < Constants.COMPARISON_THRESHOLD) {
                minD = d;
                closestIntersection = p;
            }
        }
        return closestIntersection;
    }
}

