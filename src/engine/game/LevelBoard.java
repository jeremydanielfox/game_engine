package engine.game;

import gameworld.GameWorld;
import java.util.Observer;


/**
 * This is the interface for a level board. It is intended to be used by a level container
 * in a game and thus is designed to support multiple levels.
 * 
 * @author Sierra
 *
 */
public interface LevelBoard {

    /**
     * Updates everything necessary to switch to the next level.
     */
    abstract void startNextLevel ();

    /**
     * Returns a boolean that represents if the game is over or not.
     * 
     * @return
     */
    public boolean gameOver ();

    public String getCurrentLevelMap ();

    /**
     * Updates all of the elements in the current level by one respective unit.
     */
    public void update ();

    /**
     * Adds an observer to this level board's collection of observers that are
     * notified whenever a select change occurs in the level board.
     * 
     * @param o
     */
    public void addObserver (Observer o);

    /**
     * Returns the game world that corresponds to the current level.
     * 
     * @return
     */
    public GameWorld getGameWorld ();

    /**
     * Returns the level object for the current level.
     * 
     * @return
     */
    public Level getCurrentLevel ();
}
