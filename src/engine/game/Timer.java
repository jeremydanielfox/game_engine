package engine.game;

public interface Timer {

    /**
     * This method is to be called every frame of the game. It updates the time
     * according to the original frame rate of the game.s
     */
    public void update ();

    /**
     * This method returns the total time left on the timer, in seconds.
     * 
     * @return time left in seconds 
     */
    public int getSecondsLeft ();

}
