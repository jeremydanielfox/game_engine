package engine.events;

import java.util.function.Consumer;

/**
 * An Event that is triggered by a specified frame count.
 * 
 * TimedEvents can also be triggered externally through the setCanStart method (intended for use
 * with a button to activate the event early)
 * 
 * @author Tom
 * @author Sierra
 *
 */
public abstract class TimedEvent implements Event {
    
    private static final int DEFAULT_START_TIME = -1;
    
    private int frameTrigger;
    private int frameCount;
    private Consumer<? extends Object> myAction;
    
    /**
     * 
     * @param frameTrigger frame count at which the event occurs (optional)
     * @param goals goals which trigger the event to start
     */
    public TimedEvent (double seconds) {
        frameCount = 0;
        this.frameTrigger = SecondsToFrames.getFramesForSeconds(seconds);
    }
    
    private void initializeVars(double seconds){
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

    /**
     * Sets the trigger of this event to zero so that it will start on next update call
     */
    public void setCanStart () {
        frameTrigger = 0;
    }

    /**
     * Increments the frame count by 1
     */
    public void incFrameCount () {
        frameCount++;
    }

    public void onCompleteAction () {
        // TODO Auto-generated method stub
        if (myAction!=null) {
            myAction.accept(null);
        }
    }
    
}
