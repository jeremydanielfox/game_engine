package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.goals.Goal;


public class TimedEvent implements Event {
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
    public boolean update () {
        frameCount++;
        if (canStart()) {
            
        }
    }

    private boolean canStart (int frameCount) {
        return (frameTrigger >= 0 && frameCount >= frameTrigger);
    }

}
