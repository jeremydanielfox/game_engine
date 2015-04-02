package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.goals.Goal;


public abstract class Event {
    private List<Goal> eventTriggers;
    private int frameTrigger;

    /**
     * 
     * @param frameTrigger frame count at which the event occurs (optional)
     * @param goals goals which trigger the event to start
     */
    public Event (double seconds, Goal ... triggers) {
        eventTriggers = new ArrayList<>();
        addTrigger(triggers);
        this.frameTrigger = SecondsToFrames.getFramesForSeconds(seconds);
    }

    /**
     * Absence of a frameTrigger sets the int to -1
     */
    public Event (Goal ... goals) {
        this(-1, goals);
    }

    /**
     * 
     * @return false if the event is complete
     */
    public abstract boolean update (int frameCount);

    protected boolean canStart (int frameCount) {
        if (frameTrigger >= 0 && frameCount >= frameTrigger) {
            return true;
        }
        for (Goal goal : eventTriggers) {
            if (goal.isSatisfied()) {
                return true;
            }
        }
        return false;
    }

    public void addTrigger (Goal ... goals) {
        eventTriggers.addAll(Arrays.asList(goals));
    }
}
