package engine.goals;

import java.util.Observable;
import View.Playable;

public class NotPlayingGoal extends PlayableGoal{

    public NotPlayingGoal () {
    }

    public NotPlayingGoal (Playable player) {
        super(player);
    }
    
    @Override
    public void update (Observable o, Object arg) {
        // doesn't observe anything
    }
    
    @Override
    public boolean isSatisfied(){
        return !(getPlayable().isPlaying());
    }

}
