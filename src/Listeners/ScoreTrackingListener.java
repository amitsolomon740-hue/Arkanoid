// 216700930 Amit Solomon

package Listeners;

import Collidables.Block;
import Geometry.Ball;
import Game.Counter;

/**
 * The Listeners.ScoreTrackingListener class is responsible for tracking the score in the game.
 * It implements the Listeners.HitListener interface and updates the score counter when a hit event occurs.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a Listeners.ScoreTrackingListener with a given Game.Counter for the current score.
     *
     * @param scoreCounter the Game.Counter object that tracks the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the score by 5 points.
     *
     * @param beingHit the Collidables.Block that is being hit
     * @param hitter the Geometry.Ball that is doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}