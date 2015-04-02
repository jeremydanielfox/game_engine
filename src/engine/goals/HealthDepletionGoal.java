package engine.goals;

import engine.game.Player;


/**
 * This class listens to the player and marks a boolean true if the players health
 * has depleted to less than or equal to zero.
 * 
 * @author Sierra Smith, Cosette Goldstein
 *
 */
public class HealthDepletionGoal extends PlayerGoal {

    public HealthDepletionGoal (Player player) {
        super(player);
    }

    @Override
    protected void checkCondition (Player p) {
        setIsSatisfied(p.getHealth() == 0);
    }

}
