// 216700930 Amit Solomon

package Collidables;

import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

/**
 * The Collidables.Collidable interface represents objects that can be collided with.
 * It provides methods to get the collision shape of the object and to handle
 * collisions with the object.
 */
public interface Collidable {
    /**
     * @return the collision shape of the object as
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter The ball that hit the block.
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the collidable object
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}