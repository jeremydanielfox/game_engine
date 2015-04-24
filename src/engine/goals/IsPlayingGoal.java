package engine.goals;

import java.util.Observable;
import View.Playable;


public class IsPlayingGoal extends PlayableGoal {
    public IsPlayingGoal () {
    }

    public IsPlayingGoal (Playable player) {
        super(player);
    }

    @Override
    public void update (Observable o, Object arg) {
        // doesn't observe anything
    }

    @Override
    public boolean isSatisfied () {
        return getPlayable().isPlaying();
    }
}
