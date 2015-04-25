package engine.game;

import gameworld.GameWorld;
import java.util.List;
import View.Displayable;


/**
 * This interface is designed to support anything that a level in a game would need to do.
 *
 * @author Sierra
 *
 */
public interface Level {

    /**
     * Returns true if the current level has been won based on set winning conditions for the level.
     * Otherwise returns false.
     *
     * @return
     */
    public boolean isWon ();

    /**
     * Returns true if the current level has been lost based on set losing conditions for the level.
     * Otherwise returns false.
     *
     * @return
     */
    public boolean isLost ();

    /**
     * Returns the string image name of the background image for this level.
     *
     * @return
     */
    public String getLevelBackground ();

    /**
     * Updates the level by one frame by updating all necessary components.
     */
    public void update ();

    /**
     * Returns the GameWorld object for this level.
     *
     * @return
     */
    public GameWorld getGameWorld ();

    /**
     * Returns a list of items contained by the level that implement the displayable interface.
     *
     * @return
     */
    public List<Displayable> getDisplayables ();

    /**
     * Sets the timer to the current level to be the timer passed in.
     *
     * @param t
     */
    public void addTimer (Timer t);
}
