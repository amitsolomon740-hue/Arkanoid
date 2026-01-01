// 216700930 Amit Solomon

package Game;

import Geometry.Constants;
import Geometry.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Ball;
import Collidables.Block;
import Collidables.Collidable;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.HitListener;
import Listeners.ScoreTrackingListener;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game.Game class represents the main class responsible for managing the game.
 * It handles the initialization of game elements, the game loop, and the integration of game objects.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;

    /**
     * Adds a collidable object to the game environment.
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            environment.addCollidable(c);
        }
    }

    /**
     * Adds a sprite to the sprite collection of the game.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            sprites.addSprite(s);
        }
    }


    /**
     * Initializes the game by creating game elements (Blocks and balls) and adding them to the game.
     */
    public void initialize() {
        // Initialize counters for blocks, balls, and score
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.score = new Counter();

        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("ass5", 800, 600);

        // Create listeners for removing blocks and balls
        BlockRemover br = new BlockRemover(this, this.counterBlocks);
        BallRemover ballR = new BallRemover(this, this.counterBalls);

        // Create and add the score indicator to the game and create the score tracking listener
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);

        double width = Constants.GUI_WIDTH;
        double height = Constants.GUI_HEIGHT;

        // Create and add paddle to the game
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(360, height - 25 - 20), 80, 20), Color.orange);
        paddle.addToGame(this);

        // Create and add balls to the game
        Ball b1 = new Ball(new Point(500, 500), 5, Color.BLACK, environment);
        b1.setVelocity(Velocity.fromAngleAndSpeed(315, 4));
        b1.addToGame(this);
        Ball b2 = new Ball(new Point(475, 500), 5, Color.BLACK, environment);
        b2.setVelocity(Velocity.fromAngleAndSpeed(270, 4));
        b2.addToGame(this);
        Ball b3 = new Ball(new Point(450, 500), 5, Color.BLACK, environment);
        b3.setVelocity(Velocity.fromAngleAndSpeed(225, 4));
        b3.addToGame(this);
        this.counterBalls.increase(3);

        paddle.addBall(b1);
        paddle.addBall(b2);

        // Create and add blocks to the game
        createBlocks(br, stl, 12, 0, Color.magenta);
        createBlocks(br, stl, 11, 1, Color.red);
        createBlocks(br, stl, 10, 2, Color.yellow);
        createBlocks(br, stl, 9, 3, Color.CYAN);
        createBlocks(br, stl, 8, 4, Color.pink);
        createBlocks(br, stl, 7, 5, Color.green);
        this.counterBlocks.increase(57); // =12+11+10+9+8+7

        // Create and add boundary blocks to prevent objects from leaving the game area
        Block topBoundary = new Block(new Point(0, 25), width, 25, Color.gray);
        Block leftBoundary = new Block(new Point(0, 25), 25, height, Color.gray);
        Block rightBoundary = new Block(new Point(width - 25, 25), 25, height, Color.gray);
        Block deathRegion = new Block(new Point(25, height), width - 50, 25, Color.gray);

        topBoundary.addToGame(this);
        leftBoundary.addToGame(this);
        rightBoundary.addToGame(this);
        deathRegion.addToGame(this);

        // Add the ball remover listener to the death region
        deathRegion.addHitListener(ballR);
    }



    /**
     * Creates and adds blocks to the game.
     * This method generates a specified number of blocks in a row and adds them to the game.
     *
     * @param hl1 the hit listener to be added to each block.
     * @param hl2 Another hit listener to be added to each block.
     * @param num the number of blocks to create.
     * @param numRow the row number where the blocks should be placed.
     * @param color the color of the blocks.
     */
    public void createBlocks(HitListener hl1, HitListener hl2, int num, int numRow, Color color) {
        for (int i = 0; i < num; i++) {
            //Each block is 53 wide and 25 long
            Block block = new Block(new Point(Constants.GUI_WIDTH - 25 - 53 * (i + 1), 100 + 25 * numRow),
                    53, 25, color);
            block.addToGame(this);

            // Add the hit listeners to the block
            block.addHitListener(hl1);
            block.addHitListener(hl2);
        }
    }


    /**
     * Runs the game loop, handling the animation and updating of game elements.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            // Check if all blocks are removed or all balls are lost and exits the game
            if (this.counterBlocks.getValue() == 0 || this.counterBalls.getValue() == 0) {
                this.score.increase(100);
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            // Paints the background blue
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidableFromEnvironment(c);
    }


    /**
     * Removes a sprite from the sprite collection of the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSpriteFromCollection(s);
    }
}