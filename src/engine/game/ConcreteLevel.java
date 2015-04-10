package engine.game;

import java.util.ArrayList;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.goals.Goal;
import gameworld.GameWorld;


/**
 * This class is in charge of maintaining everything that occurs within one level, such as
 * the list of events/waves to occur (Storyboard object), as well as the background image
 * and the GameWorld for the level.
 * 
 * @author Sierra Smith and Cosette Goldstein
 *
 */
@Settable
public class ConcreteLevel implements Level {

    // note to self: we need to save all of the images out to one directory
    // we should identify images by name and have a constant image path that we pin on before
    // Retrieving image
    private String myImagePath;
    private List<Goal> myWinningGoals;
    private List<Goal> myLosingGoals;
    private GameWorld myGameWorld;
    private StoryBoard myStoryBoard;

    public ConcreteLevel() {
        myImagePath = "";
        myWinningGoals = new ArrayList<Goal>();
        myLosingGoals = new ArrayList<Goal>();
        //TODO:intialize GameWorld
        myStoryBoard = new StoryBoard();
    }

    public ConcreteLevel (String image,
                          List<Goal> winningGoals,
                          List<Goal> losingGoals,
                          GameWorld gameWorld,
                          StoryBoard storyBoard) {
        myImagePath = image;
        myWinningGoals = winningGoals;
        myLosingGoals = losingGoals;
        myGameWorld = gameWorld;
        myStoryBoard = storyBoard;
    }

    public String getLevelBackground () {
        return myImagePath;
    }

    @Override
    public boolean isWon () {
        return checkGoals(myWinningGoals);
    }

    @Override
    public boolean isLost () {
        return checkGoals(myLosingGoals);
    }

    private boolean checkGoals (List<Goal> listOfGoals) {
        for (Goal goal : listOfGoals) {
            if (goal.isSatisfied()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        myGameWorld.updateGameObjects();
//        myGameWorld.checkCollisions();
//        myGameWorld.removeDeadObjects();
        // move GameObjects, needs to communicate with StoryBoard
    }

    @Override
    public GameWorld getGameWorld () {
        // TODO Auto-generated method stub
        return myGameWorld;
    }

    @Settable
    public void setImagePath (String imagePath) {
        myImagePath = imagePath;
    }

    @Settable
    public void setWinningGoals (List<Goal> goals) {
        myWinningGoals = goals;
    }

    @Settable
    public void setLosingGoals (List<Goal> goals) {
        myLosingGoals = goals;
    }

    @Settable
    public void setGameWorld (GameWorld gameworld) {
        myGameWorld = gameworld;
    }

    @Settable
    public void setStoryBoard (StoryBoard storyBoard) {
        myStoryBoard = storyBoard;
    }

}
