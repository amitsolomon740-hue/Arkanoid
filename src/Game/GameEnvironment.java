// 216700930 Amit Solomon

package Game;

import Collidables.Collidable;
import Collidables.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game.GameEnvironment class represents the environment in which the game takes place.
 * It manages a collection of collidable objects and provides methods for collision detection.
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidables;

    /**
     * Constructs a new Game.GameEnvironment object with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Returns a copy of the list of collidables in the environment.
     * @return a list of collidable objects
     */
    public java.util.List<Collidable> getCollidables() {
        return new ArrayList<>(this.collidables);
    }


    /**
     * Adds the given collidable object to the environment.
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidables.add(c);
        }
    }

    /**
     * Removes the given collidable object from the environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidableFromEnvironment(Collidable c) {
        if (c != null) {
            this.collidables.remove(c);
        }
    }

    /**
     * Determines the closest collision point between the given trajectory and any collidable object.
     * @param trajectory the trajectory of the moving object
     * @return a Collidables.CollisionInfo object containing information about the closest collision,
     * or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize variables to keep track of the closest collision information
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        Collidable closestCollidable = null;
        List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);

        for (Collidable c : collidablesCopy) {
            // Find the closest intersection point between the trajectory and the collidable rectangle
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                // Calculate the distance from the start of the trajectory to the intersection point
                double d = p.distance(trajectory.start());
                if (d < minDistance) {
                    minDistance = d;
                    closestPoint = p;
                    closestCollidable = c;
                }
            }
        }
        if (closestPoint != null) {
            return new CollisionInfo(closestPoint, closestCollidable);
        }
        return null;
    }
}
