package engine.events;

/**
 * Event interface contains information about actions triggered in game. The event has a start and
 * an end that can be queried.
 * 
 * @author Tom
 * @author Cosette
 * @author Sierra
 *
 */

public interface Event {
    public boolean update ();

    /**
     * 
     * @return true if the event's starting conditions are met
     */
    public boolean canStart ();

    /**
     * Changes the Event state to meet it's start conditions
     */
    public void setCanStart ();
}
