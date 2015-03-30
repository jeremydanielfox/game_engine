package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * This class contains all levels that exist in a game.
 * 
 * @author Sierra Smith
 *
 */
public class ConcreteLevelBoard extends Observable implements LevelBoard {

    private List<Level> myLevels;
    private int curLevelIndex;

    public ConcreteLevelBoard () {
        myLevels = new ArrayList<Level>();
        curLevelIndex = 0;
    }

    public void addLevel (Level newLevel) {
        myLevels.add(newLevel);
    }

    /**
     * This method is called every frame to update the current level and check if the level has
     * ended.
     * If it has ended, notify the View.
     */
    public void update () {

    }

    /**
     * Returns true if the final level is won or if any level is lost.
     * 
     * @return
     */
    public boolean gameOver () {
        return ((curLevelIndex == myLevels.size() - 1) && (myLevels.get(curLevelIndex).isWon())) ||
               myLevels.get(curLevelIndex).isLost();
    }

    public String getCurrentLevelMap () {
        return myLevels.get(curLevelIndex).getLevelBackground();
    }

    // note: do we really need this method? no other object knows how to deal with a "level"
    // rather, should the method just be called update current level? which can change the level
    @Override
    public void getNextLevel () {
        // TODO Auto-generated method stub

    }

}
