// 216700930 Amit Solomon

package Game;

/**
 * The Game.Counter class represents a simple counter that can be increased or decreased by a specified value.
 */

public class Counter {
    private int count;

    /**
     * Constructs a new Game.Counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number to be added to the count
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number to be subtracted from the count
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Get current count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}