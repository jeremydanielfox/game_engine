package engine.game;

import java.util.ArrayList;
import java.util.List;
import View.Displayable;
import engine.fieldsetting.Settable;
import engine.goals.EventsCompleteGoal;
import engine.goals.Goal;
import gameworld.FixedWorld;
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

    private static final int EVENT_GOAL_INDEX = 0;

    // note to self: we need to save all of the images out to one directory
    // we should identify images by name and have a constant image path that we pin on before
    // Retrieving image
    private String myTitle = "";
    private String myImagePath = "src/images/Park_Path.png";
    private List<Goal> myWinningGoals;
    private List<Goal> myLosingGoals;
    private GameWorld myGameWorld;
    private StoryBoard myStoryBoard;
    private Timer myTimer;

    public ConcreteLevel () {
        initialize("", new ArrayList<Goal>(), new ArrayList<Goal>(), new FixedWorld(10,10),
                   new StoryBoard());
    }

    public ConcreteLevel (String image,
                          List<Goal> winningGoals,
                          List<Goal> losingGoals,
                          GameWorld gameWorld,
                          StoryBoard storyBoard) {
        initialize(image, winningGoals, losingGoals, gameWorld, storyBoard);
    }

    private void initialize (String image,
                             List<Goal> winningGoals,
                             List<Goal> losingGoals,
                             GameWorld gameWorld,
                             StoryBoard storyBoard) {
        myImagePath = image;
        myWinningGoals = winningGoals;
        myLosingGoals = losingGoals;
        myGameWorld = gameWorld;
        myStoryBoard = storyBoard;
        addDefaultGoals();
    }
    
    private void addDefaultGoals(){
        myWinningGoals.add(EVENT_GOAL_INDEX, new EventsCompleteGoal(myStoryBoard));
    }

    @Override
    @Settable
    public void addTimer (Timer t) {
        myTimer = t;
    }

    @Override
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
        myGameWorld.updateGameObjects();
        myStoryBoard.update();
        if (myTimer != null) {
            myTimer.update();
        }
        // myGameWorld.checkCollisions();
        // myGameWorld.removeDeadObjects();
        // move GameObjects, needs to communicate with StoryBoard
    }

    @Override
    public GameWorld getGameWorld () {
        return myGameWorld;
    }

    @Settable
    public void setImagePath (String imagePath) {
        myImagePath = imagePath;
    }

    @Settable
    public void setWinningGoals (List<Goal> goals) {
        myWinningGoals = goals;
        addDefaultGoals();
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
        ((EventsCompleteGoal) myWinningGoals.get(EVENT_GOAL_INDEX)).setStoryBoard(storyBoard);
    }

    /**
     * Returns a list of displayable things.
     */
    @Override
    public List<Displayable> getDisplayables () {
        List<Displayable> displays = new ArrayList<>();
        if (myTimer != null) {
            displays.add(myTimer);
        }
        return displays;
    }

}
