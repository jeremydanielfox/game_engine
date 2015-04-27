package engine.goals;

import engine.fieldsetting.Settable;
import engine.game.Player;


/**
 * This class listens to the player and marks a boolean true if the players health
 * has reached the specified target or is below the target.
 *
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */
@Settable
public class HealthGoal extends PlayerGoal {

    private static double DEFAULT_HEALTH = 0;

    private double myHealthGoal;

    public HealthGoal () {
        super(new Player());
        myHealthGoal = DEFAULT_HEALTH;
    }

    public HealthGoal (Player player, double target) {
        super(player);
        myHealthGoal = target;
    }

    @Override
 //   @Settable
    public void setPlayer (Player p) {
        super.setPlayer(p);
    }

    @Settable
    public void setHealthTarget (double target) {
        myHealthGoal = target;
    }

    /**
     * Returns true if the player's health is at or below the target.
     */
    @Override
    protected void checkCondition (Player p) {
        setIsSatisfied(p.getHealth() <= myHealthGoal);
    }

}
