// 216700930 Amit Solomon

package Sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The Sprites.SpriteCollection class represents a collection of sprites.
 * It manages the addition of sprites and provides methods to notify all sprites about time passage
 * and to draw all sprites on a given DrawSurface.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> spriteCollection;

    /**
     * Constructs a new Sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<>();
    }


    /**
     * Adds a sprite to the collection.
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.spriteCollection.add(s);
        }
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method calls the timePassed() method on each sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.spriteCollection);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * This method calls the drawOn(DrawSurface) method on each sprite.
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(this.spriteCollection);
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSpriteFromCollection(Sprite s) {
        if (s != null) {
            this.spriteCollection.remove(s);
        }
    }
}