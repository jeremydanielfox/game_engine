package engine.goals;

import java.util.Observable;
import engine.fieldsetting.Settable;
import engine.game.*;


/**
 * This class listens to a timer and "is satisfied" when the timer has reached or gone below a
 * certain time.
 * 
 * @author Sierra
 *
 */
@Settable
public class TimerGoal extends Goal {

    public static final int DEFAULT_SECONDS = 0;
    
    private Timer myTimer;
    private int mySecondsGoal;

    public TimerGoal (Timer t, int secs) {
        myTimer = t;
        myTimer.addObserver(this);
        mySecondsGoal = secs;
    }
    
    public TimerGoal(){
        myTimer = new TimerConcrete();
        mySecondsGoal = DEFAULT_SECONDS;
    }

    @Settable
    public void setSecondsGoal(int secs){
        mySecondsGoal = secs;
    }
    
    @Settable
    public void setTimer(Timer t){
        myTimer = t;
        myTimer.addObserver(this);
    }
    
    @Override
    public void update (Observable o, Object arg) {
        if(o.equals(myTimer)){
            setIsSatisfied(myTimer.getSecondsLeft() <= mySecondsGoal);
        }

    }

}
