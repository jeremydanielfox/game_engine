package engine.goals;


import engine.fieldsetting.Settable;
import View.Playable;

@Settable
public abstract class PlayableGoal extends Goal {

    private Playable myPlayable;
    
    public PlayableGoal(){
    }
    
    public PlayableGoal(Playable player){
        myPlayable = player;
    }
    
    @Settable
    public void setPlayer(Playable player){
        myPlayable = player;
    }
    
    protected Playable getPlayable(){
        return myPlayable;
    }

}
