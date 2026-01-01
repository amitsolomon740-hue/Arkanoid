// 216700930 Amit Solomon

package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This class represents a rectangle defined by an upper-left corner point, a width, and a height.
 * It provides methods to calculate the intersection points of the rectangle with a line.
 */
public class Rectangle {
        private final Point upperLeft;
        private final double width;
        private final double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // calculate the four corner points of the rectangle
        Point leftUp = new Point(upperLeft.getX(), upperLeft.getY());
        Point rightUp = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point leftDown = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point rightDown = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // create lines representing the four sides of the rectangle
        Line up = new Line(leftUp, rightUp);
        Line down = new Line(leftDown, rightDown);
        Line left = new Line(leftUp, leftDown);
        Line right = new Line(rightUp, rightDown);

        // calculate intersection points with each side of the rectangle
        Point intersectionUp = up.intersectionWith(line);
        Point intersectionDown = down.intersectionWith(line);
        Point intersectionLeft = left.intersectionWith(line);
        Point intersectionRight = right.intersectionWith(line);

        java.util.List<Point> intersectionLst = new ArrayList<>();
        boolean upIsNull1 = true, downIsNull2 = true;

        // inserts non-empty points, checks that the points are not the same and inserts them only once
        // (if the intersection is on the corners of the rectangle).

        if (intersectionUp != null) {
            intersectionLst.add(intersectionUp);
            upIsNull1 = false;
        }

        if (intersectionDown != null && !(!upIsNull1 && intersectionDown.equals(intersectionUp))) {
            intersectionLst.add(intersectionDown);
            downIsNull2 = false;
        }

        if (intersectionLeft != null && !(!upIsNull1 && intersectionLeft.equals(intersectionUp))
                && !(!downIsNull2 && intersectionLeft.equals(intersectionDown))) {
            intersectionLst.add(intersectionLeft);
        }

        if (intersectionRight != null && !(!upIsNull1 && intersectionRight.equals(intersectionUp))
                && !(!downIsNull2 && intersectionRight.equals(intersectionDown))) {
            intersectionLst.add(intersectionRight);
        }
        return intersectionLst;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Draws a rectangle on the given DrawSurface.
     * This method fills the rectangle with the specified color and draws a black frame around it.
     *
     * @param c the color to fill the rectangle.
     * @param d the DrawSurface on which to draw the rectangle.
     */
    public void drawRect(Color c, DrawSurface d) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();

        //fill the rectangle
        d.setColor(c);
        d.fillRectangle(x, y, width, height);

        //Draws the frame of the rectangle
        d.setColor(Color.black);
        d.drawLine(x, y + height, x + width, y + height);
        d.drawLine(x + width, y, x + width, y + height);
        d.drawLine(x, y, x, y + height);
        d.drawLine(x, y, x + width, y);
    }
}