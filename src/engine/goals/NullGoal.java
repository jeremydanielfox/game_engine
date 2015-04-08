package engine.goals;

import java.util.Observable;

public class NullGoal extends Goal{

    @Override
    public void update (Observable o, Object arg) {
        // does nothing, because observes nothing
    }

}
