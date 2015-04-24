package engine.goals;

import java.util.Observable;
import engine.fieldsetting.Settable;
import engine.game.StoryBoard;


@Settable
public class NoCurrentEventGoal extends StoryBoardGoal {

    public NoCurrentEventGoal () {
        super();
    }

    public NoCurrentEventGoal (StoryBoard storyBoard) {
        super(storyBoard);
    }

    @Override
    @Settable
    public void setStoryBoard (StoryBoard s) {
        super.setStoryBoard(s);
    }

    @Override
    public boolean isSatisfied () {
        return !(getStoryBoard().eventInProgress());
    }

    @Override
    public void update (Observable o, Object arg) {
        // doesn't rely on updates
    }

}
