// 216700930 Amit Solomon

package Collidables;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Velocity;
import Geometry.Constants;
import Listeners.HitListener;
import Listeners.HitNotifier;
import Sprites.Sprite;
import Game.Game;

import biuoop.DrawSurface;
import Geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Collidables.Block class represents a block that can be collided with.
 * Implements the Collidables.Collidable, Sprites.Sprite, and Listeners.HitNotifier interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    private final Rectangle collisionRectangle;
    private final java.awt.Color color;

    /**
     * Constructs a Collidables.Block with a specified position and size.
     *
     * @param p The upper-left point of the block.
     * @param width The width of the block.
     * @param height The height of the block.
     * @param color The color of the block.
     */
    public Block(Point p, double width, double height, java.awt.Color color) {
        this.collisionRectangle = new Rectangle(p, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the collision rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getWidth(),
                this.collisionRectangle.getHeight());
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity. The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param hitter The ball that hit the block.
     * @param collisionPoint The point at which the collision occurs.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity expected after the hit, or null if no valid collision side is found.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // rectangle values
        Point upperLeft = this.collisionRectangle.getUpperLeft();
        double xLeft = upperLeft.getX();
        double xRight = xLeft + this.collisionRectangle.getWidth();
        double yTop = upperLeft.getY();
        double yBottom = yTop + this.collisionRectangle.getHeight();
        // collision values
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double threshold = Constants.COMPARISON_THRESHOLD;

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        // calculate corners points of the rectangle
        Point rightUp = new Point(xRight, yTop);
        Point leftDown = new Point(xLeft, yBottom);
        Point rightDown = new Point(xRight, yBottom);

        // Check if the collision point is on any of the rectangle's sides or corners
        if (collisionPoint.equals(upperLeft) || collisionPoint.equals(rightUp)
        || collisionPoint.equals(leftDown) || collisionPoint.equals(rightDown)) {
            // Collision at the corners
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else if ((Math.abs(xLeft - x) < threshold || Math.abs(xRight - x) < threshold)
        && threshold > yTop - y && y - yBottom < threshold) {
            // Collision at the left or right sides
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if ((Math.abs(yTop - y) < threshold || Math.abs(yBottom - y) < threshold)
        && x - xRight < threshold && threshold > xLeft - x) {
            // Collision at the top or bottom sides
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d The DrawSurface on which the block is drawn.
     */
    public void drawOn(DrawSurface d) {
        this.collisionRectangle.drawRect(this.color, d);
    }

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    public void timePassed() { }

    /**
     * Adds the sprite to the specified game.
     * This method is typically called to add the sprite to the game's collection of sprites and collidables,
     * allowing it to participate in collision detection and response as well as drawing and updating.
     * @param g the game to which the sprite will be added
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * @param ball The ball to check.
     * @return true if the ball's color matches the block's color, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    }

    /**
     * Removes the block from the specified game.
     *
     * @param game The game from which the block will be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds a Listeners.HitListener to the block.
     *
     * @param hl The Listeners.HitListener to be added.
     */
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    /**
     * Removes a Listeners.HitListener from the block.
     *
     * @param hl The Listeners.HitListener to be removed.
     */
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }


    /**
     * @return the color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
}
