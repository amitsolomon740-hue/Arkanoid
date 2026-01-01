// 216700930 Amit Solomon

package Sprites;

import biuoop.DrawSurface;

/**
 * The Sprites.Sprite interface represents a game object that can be drawn to the screen
 * and notified that time has passed. It defines the methods for drawing the sprite
 * and for handling the passage of time.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}