package engine.goals;

import java.util.Observable;

public class NullGoal extends Goal{

    public NullGoal () {
        setIsSatisfied(true);
    }
    
    @Override
    public void update (Observable o, Object arg) {
        // does nothing, because observes nothing
    }

}
