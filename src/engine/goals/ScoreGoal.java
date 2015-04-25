package engine.goals;

import engine.fieldsetting.Settable;
import engine.game.Player;


/**
 * This class represents a goal that listens to the player and changes a boolean flag if
 * the player's score has reached a certain value.
 *
 * @author Sierra Smith
 *
 */
@Settable
public class ScoreGoal extends PlayerGoal {

    public static final double DEFAULT_SCORE = 1000;

    private double myScoreGoal;

    public ScoreGoal () {
        super(new Player());
        myScoreGoal = DEFAULT_SCORE;
    }

    public ScoreGoal (Player player, int score) {
        super(player);
        myScoreGoal = score;
    }

    @Override
    @Settable
    public void setPlayer (Player p) {
        super.setPlayer(p);
    }

    @Settable
    public void setScoreTarget (int target) {
        myScoreGoal = target;
    }

    @Override
    protected void checkCondition (Player p) {
        setIsSatisfied(p.getScore() >= myScoreGoal);
    }

}
