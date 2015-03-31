package events;

public interface Event {
    /**
     * 
     * @return false if the event is complete
     */
    public boolean update ();
}
