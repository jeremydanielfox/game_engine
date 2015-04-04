package engine.goals;

import engine.game.Player;
import java.util.Observable;


/**
 * This class represents the head of the hierarchy of goals that listen to the player.
 * 
 * @author sierrasmith95
 *
 */
public abstract class PlayerGoal extends Goal {

    private Player myPlayer;

    public PlayerGoal (Player player) {
        myPlayer = player;
        myPlayer.addObserver(this);
    }

    @Override
    public void update (Observable o, Object arg) {
        if (myPlayer.equals(o))
            checkCondition(myPlayer);
    }

    protected abstract void checkCondition (Player p);

}
