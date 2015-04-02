package engine.goals;

import engine.game.Player;
import java.util.Observable;


/**
 * This class represents a goal that listens to the player and changes a boolean flag if
 * the player's score has reached a certain value.
 * 
 * @author Sierra Smith
 *
 */
public class ScoreGoal extends PlayerGoal {

    private int myScoreGoal;

    public ScoreGoal (Player player, int score) {
        super(player);
        myScoreGoal = score;
    }

    @Override
    protected void checkCondition (Player p) {
       setIsSatisfied(p.getScore() >= myScoreGoal);
       System.out.println(p.getScore());
    }

}
