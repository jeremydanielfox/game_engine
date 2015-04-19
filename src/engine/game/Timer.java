package engine.game;

import View.Displayable;


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
