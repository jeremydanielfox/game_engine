package engine.events;

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
