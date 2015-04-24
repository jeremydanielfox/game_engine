package engine.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.goals.Goal;


/**
 * Uses goals to determine an event's starting conditions
 *
 * @author Tom and Sierra
 *
 */
public class GoalEvent implements Event {
    private List<Goal> eventTriggers;

    public GoalEvent (Goal ... triggers) {
        eventTriggers = new ArrayList<>();
        addTrigger(triggers);
    }

    @Override
    public boolean update () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canStart () {
        for (Goal goal : eventTriggers) {
            if (!goal.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void addTrigger (Goal ... goals) {
        eventTriggers.addAll(Arrays.asList(goals));
    }

    @Override
    public void setCanStart () {
        // TODO Auto-generated method stub
        // Set all goals to be met?

    }

    @Override
    public void onCompleteAction () {
        // TODO Auto-generated method stub

    }

}
