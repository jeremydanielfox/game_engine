package engine.game;

import View.Displayable;


/**
 * A class can extend the Timer class when it will be "Displayable," be able to update when called
 * each frame, and hold a minute-second value.
 * 
 * @author Sierra
 *
 */
public abstract class Timer extends Displayable {

    /**
     * This method is to be called every frame of the game. It updates the time
     * according to the original frame rate of the game.s
     */
    public abstract void update ();

    /**
     * This method returns the total time left on the timer, in seconds.
     * 
     * @return time left in seconds
     */
    public abstract int getSecondsLeft ();

}
