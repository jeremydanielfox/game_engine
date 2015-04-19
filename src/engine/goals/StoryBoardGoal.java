package engine.goals;

import engine.fieldsetting.Settable;
import engine.game.StoryBoard;


public abstract class StoryBoardGoal extends Goal {

    private StoryBoard myStoryBoard;

    public StoryBoardGoal () {
        myStoryBoard = new StoryBoard();
    }

    public StoryBoardGoal (StoryBoard storyBoard) {
        myStoryBoard = storyBoard;
        myStoryBoard.addObserver(this);
    }

    @Settable
    public void setStoryBoard (StoryBoard s) {
        myStoryBoard = s;
    }

    protected StoryBoard getStoryBoard () {
        return myStoryBoard;
    }

}
