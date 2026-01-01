// 216700930 Amit Solomon

package Listeners;

import Collidables.Block;
import Geometry.Ball;

/**
 * A Listeners.HitListener is notified whenever an object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that is being hit
     * @param hitter the Geometry.Ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}