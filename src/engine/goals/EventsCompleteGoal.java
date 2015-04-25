package engine.goals;

import java.util.Observable;
import engine.fieldsetting.Settable;
import engine.game.StoryBoard;


public class EventsCompleteGoal extends StoryBoardGoal {

    public EventsCompleteGoal () {
        super();
    }

    public EventsCompleteGoal (StoryBoard story) {
        super(story);
    }

    @Override
    @Settable
    public void setStoryBoard (StoryBoard s) {
        super.setStoryBoard(s);
    }

    @Override
    public void update (Observable o, Object arg) {
        if (o.equals(getStoryBoard())) {
            setIsSatisfied(getStoryBoard().currentEventCount() == 0);
        }

    }

}
