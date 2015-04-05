package engine.events;



public abstract class TimedEvent implements Event {
    private int frameTrigger;
    private int frameCount;

    /**
     * 
     * @param frameTrigger frame count at which the event occurs (optional)
     * @param goals goals which trigger the event to start
     */
    public TimedEvent (double seconds) {
        frameCount = 0;
        this.frameTrigger = SecondsToFrames.getFramesForSeconds(seconds);
    }

    /**
     * 
     * @return false if the event is complete
     */
    public abstract boolean update ();

    /**
     * 
     * @return true if the appropriate frame count is reached
     */
    public boolean canStart () {
        return (frameTrigger >= 0 && frameCount >= frameTrigger);
    }
    
    public void setCanStart() {
        frameTrigger = 0;
    }

}
