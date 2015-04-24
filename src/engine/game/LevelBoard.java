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
     * Updates everything necessary to switch to the next level. After this method is called,
     * "update()" will begin updating the next level.
     */
    abstract void startNextLevel ();

    /**
     * Returns a boolean that represents if the game is over or not as determined by the
     * winning and losing conditions defined by the game.
     *
     * @return
     */
    public boolean gameOver ();

    /**
     * Returns the string name of the image for the current level.
     *
     * @return
     */
    public String getCurrentLevelMap ();

    /**
     * Updates all of the elements in the current level by one frame.
     */
    public void update ();

    /**
     * Adds an observer to this level board's collection of observers that will be
     * notified whenever a change occurs in the level board that has been defined to notify
     * observers.
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
     * Returns the level object that corresponds to the current level.
     *
     * @return
     */
    public Level getCurrentLevel ();
}
