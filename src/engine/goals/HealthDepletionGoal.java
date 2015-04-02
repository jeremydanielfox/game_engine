package engine.goals;

import engine.game.Player;
import java.util.Observable;

public class HealthDepletionGoal extends Goal {

    private Player myPlayer;
    
    public HealthDepletionGoal (Player player) {
        myPlayer=player;
        myPlayer.addObserver(this);
    } 
    
    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub
        if (myPlayer.equals(o)&&myPlayer.getHealth()<=0) {
            System.out.println("here");
            setIsSatisfied(true);
        }
    }


}
