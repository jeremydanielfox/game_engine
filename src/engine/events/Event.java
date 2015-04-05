package engine.events;

public interface Event {
    public boolean update ();

    /**
     * Changes the Event state to meet it's start conditions
     */
    public void setCanStart ();
}
