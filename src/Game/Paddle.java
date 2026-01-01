// 216700930 Amit Solomon

package Game;

import Collidables.Collidable;
import Geometry.Ball;
import Geometry.Rectangle;
import Geometry.Constants;
import Geometry.Velocity;
import Geometry.Point;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * The Game.Paddle class represents the paddle in the game, which is controlled by the keyboard.
 * The paddle is a rectangle that can move left and right and can collide with balls.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rect;
    private final java.util.List<Ball> balls; // to check if any ball will collide with the movement of the paddle.
    private final java.awt.Color color;

    /**
     * Constructs a Game.Paddle object with the specified keyboard sensor, rectangle, and color.
     *
     * @param keyboard the keyboard sensor to control the paddle
     * @param rect the rectangle representing the paddle
     * @param color the color of the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, java.awt.Color color) {
        this.keyboard = keyboard;
        this.rect = new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
        this.balls = new ArrayList<>();
        this.color = color;
    }

    /**
     * Adds a ball to the list of balls that the paddle can collide with.
     *
     * @param b the ball to be added
     */
    public void addBall(Ball b) {
        if (b != null) {
            this.balls.add(b);
        }
    }

    /**
     * Moves the paddle to the left by 5 units. If the paddle reaches the left edge,
     * it is moved to the right edge.
     */
    public void moveLeft() {
        //update the upperLeft point of the rectangle of the rectangle
        double updateX = this.rect.getUpperLeft().getX() - 5;
        if (updateX - 25 <= Constants.COMPARISON_THRESHOLD) {
            updateX = Constants.GUI_WIDTH - this.rect.getWidth() - 25 - Constants.COMPARISON_THRESHOLD;
        }
        this.rect.getUpperLeft().setX(updateX);

        //Takes the balls out of the range of motion of the paddle
        java.util.List<Ball> lst = willCollide();
        if (lst.isEmpty()) {
            return;
        }
        for (Ball b: lst) {
            if (updateX - 2 - 25 < Constants.COMPARISON_THRESHOLD) {
                b.setX(b.getSize() + 25);
            } else {
                b.setX(updateX - 2);
            }
            b.moveOneStep();
        }
    }

    /**
     * Moves the paddle to the right by 5 units. If the paddle reaches the right edge,
     * it is moved to the left edge.
     */
    public void moveRight() {
        //update the upperLeft point of the rectangle of rectangle
        double updateX = this.rect.getUpperLeft().getX() + 5;
        if (Constants.GUI_WIDTH - 25 - updateX - this.rect.getWidth() <= Constants.COMPARISON_THRESHOLD) {
            updateX = Constants.COMPARISON_THRESHOLD + 25;
        }
        this.rect.getUpperLeft().setX(updateX);

        //Takes the balls out of the range of motion of the paddle
        java.util.List<Ball> lst = willCollide();
        if (lst.isEmpty()) {
            return;
        }
        for (Ball b: lst) {
            if (Constants.GUI_WIDTH - updateX - this.rect.getWidth() - 2 - 25 < Constants.COMPARISON_THRESHOLD) {
                b.setX(Constants.GUI_WIDTH - 25 - b.getSize());
            } else {
                b.setX(updateX + this.rect.getWidth() + 2);
            }
            b.moveOneStep();
        }
    }

    // Sprites.Sprite

    /**
     * Notifies the paddle that a time unit has passed, checking if the left or right keys
     * are pressed to move the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        this.rect.drawRect(this.color, d);
    }

    // Collidables.Collidable

    /**
     * @return the collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rect.getUpperLeft(), this.rect.getWidth(),
                this.rect.getHeight());
    }

    /**
     * Handles the collision of the paddle with a ball. The ball's velocity changes
     * based on which region of the paddle it collides with.
     *
     *@param hitter The ball that hit the block.
     * @param collisionPoint the point at which the collision occurs
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = this.rect.getWidth() / 5;
        double xLeft = this.rect.getUpperLeft().getX();
        double xRight = xLeft + this.rect.getWidth();
        double yTop = this.rect.getUpperLeft().getY();
        double xCollision = collisionPoint.getX();
        double yCollision = collisionPoint.getY();

        // Check if the collision occurs at the top of the block
        if (Math.abs(yTop - yCollision) <= Constants.COMPARISON_THRESHOLD && regionWidth != 0) {
            int regionNum = (int) ((xCollision - xLeft) / regionWidth) + 1;
            // Set the angle based on the collision region
            double angle = 270;
            if (regionNum == 1) {
                angle = 210;
            }
            if (regionNum == 2) {
                angle = 240;
            }
            if (regionNum == 4) {
                angle = 300;
            }
            if (regionNum == 5 || Math.abs(xRight - xCollision) < Constants.COMPARISON_THRESHOLD)  {
                angle = 330;
            }
            double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy());
            return Velocity.fromAngleAndSpeed(angle, speed);
        }
        // If the collision is not at the top, invert the horizontal velocity (because it's on the sides).
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * Adds this paddle to the game, as both a Collidables.Collidable and a Sprites.Sprite.
     *
     * @param g the game to which the paddle is added
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Checks if a given ball is within the bounds of the paddle.
     *
     * @param b the ball to check
     * @return true if the ball is within the bounds of the paddle, false otherwise
     */
   public boolean isBallInRect(Ball b) {
        double yTop = this.rect.getUpperLeft().getY();
        double yBottom = yTop + this.rect.getHeight();
        double xLeft = this.rect.getUpperLeft().getX();
        double xRight = xLeft + this.rect.getWidth();

        double xBall = b.getX();
        double yBall = b.getY();

       // Check if the ball's center is within the rectangle's boundaries
        return yBall <= yBottom && yBall >= yTop && xBall <= xRight && xBall >= xLeft;
    }

    /**
     * Checks if any balls are colliding with the paddle.
     *
     * @return a list of balls that are colliding with the paddle
     */
    public java.util.List<Ball> willCollide() {
        java.util.List<Ball> lst = new ArrayList<>();
        for (Ball b : this.balls) {
            if (isBallInRect(b)) {
                lst.add(b);
            }
        }
        return lst;
    }
}
