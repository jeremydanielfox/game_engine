package engine.game;

import View.ViewConcrete2;
import engine.fieldsetting.Settable;


/**
 * This class is a timer that counts down at the starting frames per second rate of the game.
 *
 * @author Sierra Smith
 *
 */
@Settable
public class TimerConcrete extends Timer {

    public static final String DEFAULT_LABEL = "Time left";
    private static final String ZERO_SEC_PREFIX = "0";
    private static final String COLON = ":";

    public static final int MINUTE_DEFAULT = 0;
    public static final int SECOND_DEFAULT = 0;
    private static final int STARTING_SECS = 59;
    private static final int MIN_TWO_DIGIT_SECS = 10;
    private static final int SECONDS_PER_MIN = 60;
    private static final int MIN_VALUE = 0;

    private int myMinutes;
    private int mySeconds;
    private String myLabel;
    private int myFramesSinceUpdate;

    public TimerConcrete () {
        initialize(MINUTE_DEFAULT, SECOND_DEFAULT, DEFAULT_LABEL);
    }

    public TimerConcrete (int min, int sec, String label) {
        initialize(min, sec, label);
    }

    @Settable
    public void setLabel (String label) {
        myLabel = label;
    }

    @Settable
    public void setMinutes (int mins) {
        myMinutes = mins;
    }

    @Settable
    public void setSeconds (int secs) {
        mySeconds = secs;
    }

    private void initialize (int min, int sec, String label) {
        myMinutes = min;
        mySeconds = sec;
        myLabel = label;
    }

    @Override
    public String getStringValue () {
        if (mySeconds < MIN_TWO_DIGIT_SECS) {
            return myMinutes + COLON + ZERO_SEC_PREFIX + mySeconds;
        }
        return myMinutes + COLON + mySeconds;
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    /**
     * Updates the time left on the clock based on the default frames per second in the
     * game loop.
     */
    @Override
    public void update () {
        myFramesSinceUpdate++;
        if (myFramesSinceUpdate == ViewConcrete2.DEFAULT_FRAMES_SECOND) {
            if (mySeconds > MIN_VALUE) {
                mySeconds--;
            }
            else if (myMinutes > MIN_VALUE) {
                mySeconds = STARTING_SECS;
                myMinutes--;
            }
            myFramesSinceUpdate = MIN_VALUE;
        }
        updateObservers();
    }

    @Override
    public int getSecondsLeft () {
        return mySeconds + (myMinutes * SECONDS_PER_MIN);
    }

    private void updateObservers () {
        setChanged();
        notifyObservers();
    }

}
