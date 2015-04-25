package View;

import javafx.scene.Node;


/**
 * This interface is meant to be implemented by any basic front end class for a game which enables
 * pause and play abilities.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
public interface EngineView {

    /**
     * Prepares the game for game play.
     */
    public abstract void initializeGameWorld ();

    /**
     * The game will be in a paused state after this method call, no matter what the previous state
     * was.
     */
    public abstract void pause ();

    /**
     * Plays the game if it is currently paused, otherwise game remains in playing state.
     */
    public abstract void play ();

    /**
     * Sets up the graphics to display the game and returns a root node that can then be displayed.
     * 
     * @return
     */
    public abstract Node initializeView ();
}
