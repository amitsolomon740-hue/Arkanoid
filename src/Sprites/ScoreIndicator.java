// 216700930 Amit Solomon

package Sprites;

import Geometry.Constants;
import Geometry.Point;
import biuoop.DrawSurface;
import Geometry.Rectangle;
import Game.Counter;
import Game.Game;

import java.awt.Color;

/**
 * The Sprites.ScoreIndicator class is responsible for displaying the current score at the top of the screen.
 * It implements the Sprites.Sprite interface and holds a reference to a Game.Counter object that tracks the score.
 */
public class ScoreIndicator implements Sprite {
    private final Counter scores;
    private final Rectangle rect;

    /**
     * Constructs a Sprites.ScoreIndicator with a given Game.Counter for the scores.
     *
     * @param scores the Game.Counter object that tracks the score
     */
    public ScoreIndicator(Counter scores) {
        this.scores = scores;
        this.rect = new Rectangle(new Point(0, 0), Constants.GUI_WIDTH, 25);
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */
    public void drawOn(DrawSurface d) {
        this.rect.drawRect(Color.white, d);
        d.setColor(Color.BLACK);
        d.drawText(350, 20, "Score: " + this.scores.getValue(), 20);
    }

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    public void timePassed() { }

    /**
     * Adds the score indicator to the specified game.
     *
     * @param game the game to which the score indicator will be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
