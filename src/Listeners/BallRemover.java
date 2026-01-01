// 216700930 Amit Solomon

package Listeners;

import Collidables.Block;
import Geometry.Ball;
import Game.Counter;
import Game.Game;

/**
 * A Listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a Listeners.BlockRemover with the specified game and counter.
     *
     * @param game The game from which blocks will be removed.
     * @param remainingBalls The counter that keeps track of the number of remaining blocks.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Also remove this listener from the block that is being removed from the game.
     *
     * @param beingHit The block that is being hit.
     * @param hitter The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}