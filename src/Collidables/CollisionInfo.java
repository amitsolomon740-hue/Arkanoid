// 216700930 Amit Solomon

package Collidables;

import Geometry.Point;

/**
 * Collidables.CollisionInfo class represents the information about a collision event.
 * It includes the point at which the collision occurs and the collidable object
 * involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructs a Collidables.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}