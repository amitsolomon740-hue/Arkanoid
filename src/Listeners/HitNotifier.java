// 216700930 Amit Solomon

package Listeners;

/**
 * A Listeners.HitNotifier interface that allows objects to add and remove HitListeners.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the Listeners.HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the Listeners.HitListener to remove
     */
    void removeHitListener(HitListener hl);
}