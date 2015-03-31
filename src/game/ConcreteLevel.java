package game;

import java.util.List;
import engine.goals.Goal;

/**
 * This class is in charge of maintaining everything that occurs within one level, such as
 * the list of events/waves to occur (Storyboard object), as well as the background image
 * and the GameWorld for the level.
 * 
 * @author Sierra Smith and Cosette Goldstein
 *
 */
public class ConcreteLevel implements Level {

    // note to self: we need to save all of the images out to one directory
    // we should identify images by name and have a constant image path that we pin on before
    // Retrieving image
    private String myImagePath;
    private List<Goal> myWinningGoals;
    private List<Goal> myLosingGoals;

    // level also needs a story board and game world

    public ConcreteLevel (String image, List<Goal> winningGoals,List<Goal> losingGoals) {
        myImagePath = image;
        myWinningGoals=winningGoals;
        myLosingGoals=losingGoals;
    }
    
    public String getLevelBackground(){
        return myImagePath;
    }

    @Override
    public boolean isWon () {
        System.out.println("result of isWon:"+checkGoals(myWinningGoals));
        return checkGoals(myWinningGoals);
    }

    @Override
    public boolean isLost () {
        System.out.println("result of isLost:"+checkGoals(myLosingGoals));
        return checkGoals(myLosingGoals);
    }

    private boolean checkGoals (List<Goal> listOfGoals) {
        for (Goal goal: listOfGoals) {
            if (goal.isSatisfied()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void update () {
        // TODO Auto-generated method stub
        //move GameObjects, needs to communicate with StoryBoard
    }

}
