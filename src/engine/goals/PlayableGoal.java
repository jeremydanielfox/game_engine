package engine.goals;

import View.Playable;
import engine.fieldsetting.Settable;


@Settable
public abstract class PlayableGoal extends Goal {

    private Playable myPlayable;

    public PlayableGoal () {
    }

    public PlayableGoal (Playable player) {
        myPlayable = player;
    }

    @Settable
    public void setPlayer (Playable player) {
        myPlayable = player;
    }

    protected Playable getPlayable () {
        return myPlayable;
    }

}
