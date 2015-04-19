package engine.game;

import engine.fieldsetting.Settable;
import View.Displayable;
import View.ViewConcrete2;


/**
 * This class is a timer that counts down at the starting frames per second rate of the game.
 * 
 * @author Sierra
 *
 */
@Settable
public class TimerConcrete extends Displayable implements Timer {

    public static final String DEFAULT_LABEL = "Time left";
    public static final int MINUTE_DEFAULT = 0;
    public static final int SECOND_DEFAULT = 0;

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
    public void setLabel(String label){
        myLabel = label;
    }
    
    @Settable
    public void setMinutes(int mins){
        myMinutes = mins;
    }
    
    @Settable
    public void setSeconds(int secs){
        mySeconds = secs;
    }

    private void initialize (int min, int sec, String label) {
        myMinutes = min;
        mySeconds = sec;
        myLabel = label;
    }

    @Override
    public String getStringValue () {
        return myMinutes + ":" + mySeconds;
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    @Override
    public void update () {
        myFramesSinceUpdate++;
        if(myFramesSinceUpdate == ViewConcrete2.DEFAULT_FRAMES_SECOND){
            if(mySeconds > 0){
                mySeconds--;
            }
            else if (myMinutes > 0){
                mySeconds = 60;
                myMinutes--;
            }
        }
    }

    @Override
    public int getSecondsLeft () {
        return mySeconds + (myMinutes * 60);
    }

}
