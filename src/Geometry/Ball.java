// 216700930 Amit Solomon

package Geometry;

import Game.Game;
import Game.GameEnvironment;
import Sprites.Sprite;
import Collidables.CollisionInfo;

import biuoop.DrawSurface;

/**
 * The Geometry.Ball class represents a ball.
 * It includes properties such as its center, size, color, velocity, and its position within frames.
 */
public class Ball implements Sprite {
    private Point center; //location
    private final int size; // radius
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a new Geometry.Ball with the specified center, radius, and color.
     * @param center The center point of the ball.
     * @param r The radius of the ball.
     * @param color The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.v = new Velocity(5, 5); // a default velocity
    }

    /**
     * Constructs a new Geometry.Ball with the specified center point, radius, color, and game environment.
     *
     * @param center The center point of the ball.
     * @param r The radius of the ball.
     * @param color The color of the ball.
     * @param game The game environment in which the ball exists.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(center.getX(), center.getY());
        this.size = r;
        this.color = color;
        this.v = new Velocity(5, 5);
        this.gameEnvironment = game;
    }

    /**
     * Sets the x value of the center of the Geometry.Ball.
     *
     * @param x the new x value of the center
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * @return The x value of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return The y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return The size (radius) of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface The DrawSurface to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * Sets the velocity of the ball.
     * @param v The velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Moves the ball one step according to its velocity and the near borders.
     */
    public void moveOneStep() {
        //compute the ball trajectory (the trajectory is "how the ball will move
        // without any obstacles" -- it is a line starting at current location, and
        //ending where the velocity will take the ball if no collisions will occur).
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);

        //Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        if (info == null) {
            this.center = nextCenter;
        } else { //Otherwise (there is a hit): move the ball to "almost" the hit point, but just slightly before it.
            Point closetedIntersection = trajectory.closestIntersectionToStartOfLine(info.collisionObject().
                    getCollisionRectangle());
            if (closetedIntersection != null) {
                double directionDx = (this.v.getDx() != 0) ? this.v.getDx() / Math.abs(this.v.getDx()) : 0;
                double directionDy = (this.v.getDy() != 0) ? this.v.getDy() / Math.abs(this.v.getDy()) : 0;
                this.center.setX(this.center.getX() - directionDx * Constants.COMPARISON_THRESHOLD);
                this.center.setY(this.center.getY() - directionDy * Constants.COMPARISON_THRESHOLD);
            }
            //notify the hit object (using its hit() method) that a collision occurred.
            //update the velocity to the new velocity returned by the hit() method.
            this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
        }
    }

    /**
     * This method is responsible for advancing the sprite's state according to the passage of time.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the sprite to the specified game.
     * This method is typically called to add the sprite to the game's collection of sprites,
     * so that it can be updated and drawn during the game.
     * @param g the game to which the sprite will be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the color of the ball.
     *
     * @param c the new color to set
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * Removes this sprite from the specified game.
     *
     * @param g the game from which the sprite will be removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}