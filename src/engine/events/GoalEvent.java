package engine.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.goals.Goal;

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
    
    private boolean canStart() {
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

}
